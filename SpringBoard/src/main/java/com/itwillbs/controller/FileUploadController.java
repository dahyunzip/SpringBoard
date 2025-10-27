package com.itwillbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	// 파일 업로드 동작 뷰페이지
	@RequestMapping(value="/uploadForm", method=RequestMethod.GET)
	public void uploadFormGET() throws Exception{
		logger.info(" uploadFormGET() 실행 ");
		logger.debug(" views/uploadForm.jsp 이동");
	}
	
	// 업로드 파일이 저장될 경로
	private static final String UPLOAD_PATH = "c:\\spring\\upload";
	//private static final String UPLOAD_PATH = "/var/spring/upload"; //리눅스
	// * 서버를 클린하면 기존 업로드 정보가 날아감
	
	// 파일 업로드 정보를 처리 - POST
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String fileUploadPOST(Model model,
							    MultipartHttpServletRequest multiRequest) throws Exception{
		logger.info(" 폼태그 submit -> fileUploadPost() 실행 ");
		
		// 한글처리 인코딩 
		// => 파일 업로드 처리는 필터 인코딩 처리를 수행하지 않는 대표적인 예외 케이스
		// => 별도로 한글처리 인코딩이 필요함
		multiRequest.setCharacterEncoding("UTF-8");
		
		// 파라메터 정보를 저장
		Map<String, Object> paramMap = new HashMap<>();
		
		// .getParameterNames() : 파라메터를 하나하나 들고 오지 않고 전부 들고 옴
		// 						  전달된 파라메터의 이름들을 모두 가져옴(파일정보 제외)
		// Enumeration : map, set 같이 인덱스를 받을 수 없는 것들을 배열로 받게 해줌
		Enumeration<String> enu = multiRequest.getParameterNames();
		
		while(enu.hasMoreElements()) {
			String name = enu.nextElement();
			//logger.debug("name : " + name);
			String value = multiRequest.getParameter(name);
			logger.debug(" name : " + name + " value : " + value);
			
			// 파라메터 정보를 Map에 저장
			paramMap.put(name, value);
		}
		
		logger.info("pramMap : {}", paramMap);
		
		// 파일 업로드
		List<String> fileList =  fileUploadProcess(multiRequest);
		
		paramMap.put("fileList", fileList);
		
		// (미구현 기능) DB에 저장하는 기능
		
		model.addAttribute("paramMap",paramMap);
		// 리스트나 컬렉션은 이름을 정해주는게 좋다.
		
		return "/result"; // 뷰페이지(/views/result.jsp) 결정
	}
	
	private List<String> fileUploadProcess(MultipartHttpServletRequest multiRequest) throws Exception{
		
		// 1) 전달된(업로드된) 파일정보를 업로드
		// UPLOAD_PATH를 절대경로로 변경, 불필요한 경로를 제거
		// 업로드할 폴더를 준비
		//Path base = Paths.get(UPLOAD_PATH).toAbsolutePath().normalize();
											// 절대 경로를 계산한다음
														  // 불필요한 경로를 제거
		// 업로드할 폴더를 생성
		//Files.createDirectories(base);
		
		// 업로드할 파일의 이름을 저장
		List<String> fileList = new ArrayList<>();
		
		// 업로드된 파일 파라메터 정보를 저장
		// Iterater : set 타입을 반복문 돌릴 수 있음.
		Iterator<String> fileNames = multiRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileParamName = fileNames.next();
			//logger.info("fileParamName : " + fileParamName );
			
			//파일을 임시 저장
			MultipartFile mFile = multiRequest.getFile(fileParamName);
			if(mFile == null || mFile.isEmpty()) {
				// 업로드한 파일의 정보가 없을 경우 다음 루프로 이동
				continue;
			}//if
			
			// 업로드한 파일의 원본이름을 저장
			// requireNonNull(객체, "메세지")
			// => 객체의 정보가 있을때는 객체타입의 정보를 리턴
			//    				없을때는 "메세지"를 사용해서 예외 발생
			String oFileName =  Objects.requireNonNull(mFile.getOriginalFilename(), "업로드된 파일정보가 없음! ");
			// 존재하면 mFile.getOriginalFilename() 리턴, 없으면 "업로드된 파일정보가 없음! "
			
			// 업로드할 파일의 이름 저장
			fileList.add(oFileName);

			// 해당 이름의 빈파일 생성
			File file = new File(UPLOAD_PATH + "\\" + oFileName);
			
			if(!file.exists()) {
				// 파일이 없으면 만들어라
				if(file.getParentFile().mkdirs()) {
					file.createNewFile();
				}
			}
			
			// 임시 파일을 실제 업로드 경로로 전달
			// 깡통 파일로 파라메터 임시 파일을 보내겠다.
			mFile.transferTo(file);
			logger.info(" 파일 업로드 성공 ! ");
		}//while
		
		logger.info("fileList : " + fileList);

		// 2) 업로드된 파일정보(이름)를 리턴
		return fileList;
	}// fileUploadProcess

	 // 다운로드 - GET
	// http://localhost:8088/download?fileName=f_site_close.png
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void fileDownloadGET(@RequestParam("fileName") String fileName,
								HttpServletResponse response) throws Exception{
								// response : 응답객체
		logger.info("/download -> fileDownloadGET() 실행!");
		
		// 다운로드할 파일의 이름을 가져오기
		logger.info(" fileName : " + fileName);
		
		// 1) 업로드해놓은 폴더에 접근, 2) 해당 파일을 찾아서 열기
		// c:\\spring\\upload\\파일이름
		// 1) File 이란게 생기면 접근이 가능해진다.
		File downFile = new File(UPLOAD_PATH + "\\" + fileName);
		
		// 파일 다운로드 처리시 필요한 옵션
		response.setHeader("Cache-Control", "no-cache");
		
		// 파일의 이름 정보를 인코딩(한글파일 처리)
		String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
		
		// 모든 파일이 다운로드창 형태로 동작
		response.addHeader("Content-disposition", "attachment; fileName=" +encodedFileName);
		
		// 파일의 내용을 다른 파일로 복사
		// 파일 읽어오는 통로 
		FileInputStream fis = new FileInputStream(downFile);
		
		// 파일 출력
		// 내보내는 통로
		OutputStream out = response.getOutputStream();

		// 출력 버퍼 생성
		byte[] buffer = new byte[1024 * 8]; // 8KB
		
		//while(true) {
		//	int data = fis.read(buffer); //파일을 열어서 buffer 만큼씩 퍼서 읽을 것이다.
		//	if(data == -1) break; // -1은 EOF(End Of File) 파일의 끝
			
			// 정보 출력
		//	out.write(buffer, 0, data);
		//}
		int data = 0;
		while((data = fis.read(buffer)) != -1) {
			// 정보출력
			out.write(buffer, 0, data);
		}
		
		out.flush(); // 빈공간에 공백을 채워서 전달
		
		logger.info(" 파일 다운로드 끝! ");
		fis.close();
		out.close();
	}
	
	// 다운로드(썸네일) - GET
	// http://localhost:8088/thumbNail?fileName=f_site_close.png
	@RequestMapping(value="/thumbNail", method=RequestMethod.GET)
	public void thumbNailDownloadGET(@RequestParam("fileName") String fileName,
								HttpServletResponse response) throws Exception{
		logger.info("/download -> thumbNailDownloadGET() 실행!");
		
		// 다운로드할 파일의 이름을 가져오기
		logger.info(" fileName : " + fileName);

		// JAVA.txt에서 lastIndexOf가 끝부터 "."의 위치를 찾는다.
		//int lastIndex = fileName.lastIndexOf(".");
		//String thumbNailName = fileName.substring(0, lastIndex);
		// => 파일에 확장자를 제외한 이름을 구할 수 있다.
		
		// 파일 출력
		// 내보내는 통로
		OutputStream out = response.getOutputStream();
		
		// 1) 업로드해놓은 폴더에 접근, 2) 해당 파일을 찾아서 열기
		// c:\\spring\\upload\\파일이름
		// 1) File 이란게 생기면 접근이 가능해진다.
		File downFile = new File(UPLOAD_PATH + "\\" + fileName);
		
		// downFile.delete();
				
		// 파일의 내용을 다른 파일로 복사
		// 파일 읽어오는 통로 
		// FileInputStream fis = new FileInputStream(downFile);
		
		// 썸네일 파일 생성
		//File thumbFile = new File(UPLOAD_PATH + "\\" + "thumbnail" + "\\" + thumbNailName+".png");
		
		if(downFile.exists()) {
			// 다운파일이 존재한다면,
			//thumbFile.getParentFile().mkdirs();
			
			// 썸네일 생성
			//Thumbnails.of(downFile).size(50, 50).outputFormat("png").toFile(thumbFile);
			Thumbnails.of(downFile).size(50, 50).outputFormat("png").toOutputStream(out);
		}
		
		logger.info(" 파일 썸네일 생성 끝! ");
		//fis.close();
		out.close();
	}
	
} // class
