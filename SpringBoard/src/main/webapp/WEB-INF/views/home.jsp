<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>

<div class="content">
	<h1>Hello world! 안녕하세요.</h1>

	<P>The time on the server is ${serverTime}.</P>

	<div class="small-box bg-aqua">
		<div class="inner">
			<h3>150</h3>
			<p>New Orders</p>
		</div>
		<div class="icon">
			<i class="ion ion-bag"></i>
		</div>
		<a href="#" class="small-box-footer">More info <i
			class="fa fa-arrow-circle-right"></i></a>
	</div>

	<div class="box box-widget widget-user-2">
		<!-- Add the bg color to the header using any of the bg-* classes -->
		<div class="widget-user-header bg-yellow">
			<div class="widget-user-image">
				<img class="img-circle" src="../dist/img/user7-128x128.jpg" alt="User Avatar">
			</div>
			<!-- /.widget-user-image -->
			<h3 class="widget-user-username">Nadia Carmichael</h3>
			<h5 class="widget-user-desc">Lead Developer</h5>
		</div>
		<div class="box-footer no-padding">
			<ul class="nav nav-stacked">
				<li><a href="#">Projects <span class="pull-right badge bg-blue">31</span></a></li>
				<li><a href="#">Tasks <span class="pull-right badge bg-aqua">5</span></a></li>
				<li><a href="#">Completed Projects <span class="pull-right badge bg-green">12</span></a></li>
				<li><a href="#">Followers <span class="pull-right badge bg-red">842</span></a></li>
			</ul>
		</div>
	</div>
</div>
<%@ include file="include/footer.jsp"%>