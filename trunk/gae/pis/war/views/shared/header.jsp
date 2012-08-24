<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div class="header">
				<div class="logo"></div>
				<div class="languageselector"></div>
				<div class="links">
					<a href="/home">Home</a> <span class="separator">|</span><a>Clear
						Cache</a>
				</div>
				<div class="login-info">
					<a href="https://appengine.google.com/dashboard?&app_id=s~daixuf"><%=request.getUserPrincipal().getName() %></a> <a href="/logout">Logout?</a>
				</div>
			</div>
			<div class="clear"></div>
			<div class="header-menu">
				<ul class="sf-menu">
					<li><a title="View the dashboard" href="/"> <img
							alt="Dashboard" src="/images/ico-dashboard.png" />Dashboard
					</a></li>
					<li><a title="Catalog Home" href="/home"> <img alt="Catalog"
							src="/images/ico-catalog.png" /> Daily Pay
					</a>
						<ul>
							<li><a title="科目管理" href=""> 科目</a></li>
							<li><a title="Products Home" href="/dailypay"> Daily Pay</a></li>
							<li><a title="Attributes Home" href=""> Attributes</a>
								<ul>
									<li><a title="Manage Product Attributes" href=""> Product
											Attributes</a></li>
									<li><a title="Manage Specification Attributes" href="">
											Product Specifications</a></li>
									<li><a title="Manage Checkout Attributes" href="">
											Checkout Attributes</a></li>
								</ul></li>
							<li><a title="Manage Product Manufacturers" href="">
									Manufacturers</a></li>
						</ul></li>
					<li><a title="Configuration Home" href=""> <img
							alt="Configuration" src="/images/ico-configuration.png" /> Account
					</a>
						<ul>
							<li><a title="Configure Global Settings" href="/login.mvc">
									Sign in</a></li>
							<li><a title="Configure Email Accounts"
								href="/user/changepasword"> Change Password</a></li>
							<li><a title="Manage banned IP addresses and networks" href="#">
									Blacklist</a></li>
							<li><a title="Payment Settings Home" href="#"> Payment</a>
								<ul>
									<li><a title="Manage Credit Cards" href="#"> Credit Cards</a></li>
									<li><a title="Manage Payment Methods" href="#"> Payment
											Methods</a></li>
								</ul></li>
						</ul></li>
					<li><a title="System Home" href="#"> <img alt="System"
							src="/images/ico-system.png" /> System
					</a>
						<ul>
							<li><a title="User Management" href="/user"> User
									Management</a></li>
							<li><a title="Category" href="/category"> Category
									Management</a></li>
							<li><a title="Maintenance" href="/dictionary"> Dictionary
									Management</a></li>
						</ul></li>
					<li><a title="Help Home" href="#"> <img alt="Help"
							src="/images/ico-help.png" /> Help
					</a>
						<ul>
							<li><a title="View Help Topics" href="#"> Help Topics</a></li>
							<li><a title="Visit nopCommerce Community Forum" href="#">
									Community Forums</a></li>
						</ul></li>
				</ul>
			</div>