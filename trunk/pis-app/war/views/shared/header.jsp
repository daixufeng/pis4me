<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>个人应用中心</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <script type="text/javascript" src="/js/jquery-1.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/style/idTabs.css"/>
    <link rel="stylesheet" type="text/css" href="/style/styles.css"/>
</head>
<body>
    <div class="master-wrapper-page">
        <div class="master-wrapper-content">
<div class="header">
    <div class="logo">
    </div>
    <div class="languageselector">
    </div>
    <div class="links">
        <a href="/home">Home</a> <span class="separator">|</span><a>Clear Cache</a></div>
    <div class="login-info">
        <%= request.getUserPrincipal().getName()%> <a href="/logout">
            Logout?</a></div>
    
    </div>
    <div class="clear">
    </div>
    <div class="header-menu">
        <ul class="sf-menu">
            <li><a title="View the dashboard" href="">
                <img alt="Dashboard" src="/images/ico-dashboard.png"/>Home</a></li>
            <li><a title="Catalog Home" href="/home.mvc">
                <img alt="Catalog" src="/images/ico-catalog.png"/>
                Catalog</a>
                <ul>
                    <li><a title="Manage Product Categories">
                        Categories</a></li>
                    <li><a title="Products Home" href="">
                        Products</a>
                        <ul>
                            <li><a title="Manage Products" href="">
                                Manage Products</a></li>
                            <li><a title="Bulk Edit Products" href="">
                                Bulk Edit Products</a></li>
                            <li><a title="Manage Product Reviews" href="">
                                Manage Reviews</a></li>
                            <li><a title="Manage Product Tags" href="">
                                Product Tags</a></li>
                            <li><a title="View Low Product Stock Report" href="">
                                Low Stock Report</a></li></ul>
                    </li>
                    <li><a title="Attributes Home" href="">
                        Attributes</a>
                        <ul>
                            <li><a title="Manage Product Attributes" href="">
                                Product Attributes</a></li>
                            <li><a title="Manage Specification Attributes" href="">
                                Product Specifications</a></li>
                            <li><a title="Manage Checkout Attributes" href="">
                                Checkout Attributes</a></li></ul>
                    </li>
                    <li><a title="Manage Product Manufacturers" href="">
                        Manufacturers</a></li></ul>
            </li>
            <li><a title="Configuration Home" href="">
                <img alt="Configuration" src="/images/ico-configuration.png">
                Account</a>
                <ul>
                    <li><a title="Configure Global Settings" href="/login.mvc">
                        Sign in</a></li>
                    <li><a title="Configure Email Accounts" href="/user/changepasword.mvc" target="desktop">
                        Change Password</a></li>
                    <li><a title="Manage banned IP addresses and networks" href="http://localhost:11139/administration/blacklist.aspx">
                        Blacklist</a></li>
                    <li><a title="Payment Settings Home" href="http://localhost:11139/administration/paymentsettingshome.aspx">
                        Payment</a>
                        <ul>
                            <li><a title="Manage Credit Cards" href="http://localhost:11139/administration/creditcardtypes.aspx">
                                Credit Cards</a></li>
                            <li><a title="Manage Payment Methods" href="http://localhost:11139/administration/paymentmethods.aspx">
                                Payment Methods</a></li></ul>
                    </li>
                    <li><a title="Tax Home" href="http://localhost:11139/administration/taxsettingshome.aspx">
                        Tax</a>
                        <ul>
                            <li><a title="Manage Tax Settings" href="http://localhost:11139/administration/taxsettings.aspx">
                                Tax Settings</a></li>
                            <li><a title="Manage Tax Providers" href="http://localhost:11139/administration/taxproviders.aspx">
                                Tax Providers</a></li>
                            <li><a title="Manage Product Tax Classes" href="http://localhost:11139/administration/taxcategories.aspx">
                                Tax Classes</a></li></ul>
                    </li>
                    <li><a title="Shipping Settings Home" href="http://localhost:11139/administration/shippingsettingshome.aspx">
                        Shipping</a>
                        <ul>
                            <li><a title="Manage Shipping Settings" href="http://localhost:11139/administration/shippingsettings.aspx">
                                Shipping Settings</a></li>
                            <li><a title="Manage Shipping Methods" href="http://localhost:11139/administration/shippingmethods.aspx">
                                Shipping Methods</a></li>
                            <li><a title="Manage Shipping Rate Computation Methods" href="http://localhost:11139/administration/shippingratecomputationmethods.aspx">
                                Shipping Rate Computation</a></li></ul>
                    </li>
                    <li><a title="Location Settings Home" href="http://localhost:11139/administration/locationsettingshome.aspx">
                        Location</a>
                        <ul>
                            <li><a title="Manage Countries" href="http://localhost:11139/administration/countries.aspx">
                                Countries</a></li>
                            <li><a title="Manage States / Provinces" href="http://localhost:11139/administration/stateprovinces.aspx">
                                States/Provinces</a></li>
                            <li><a title="Configure Language Settings" href="http://localhost:11139/administration/languages.aspx">
                                Languages</a></li>
                            <li><a title="Manage Store Currencies" href="http://localhost:11139/administration/currencies.aspx">
                                Currencies</a></li>
                            <li><a title="Manage Warehouses" href="http://localhost:11139/administration/warehouses.aspx">
                                Warehouses</a></li></ul>
                    </li>
                    <li><a title="Manage Measures" href="http://localhost:11139/administration/measures.aspx">
                        Measures</a></li>
                    <li><a title="Activity Log Home" href="http://localhost:11139/administration/activityloghome.aspx">
                        Activity Log</a>
                        <ul>
                            <li><a title="Manage Activity Types" href="http://localhost:11139/administration/activitytypes.aspx">
                                Activity Types</a></li>
                            <li><a title="View Activity Log" href="http://localhost:11139/administration/activitylog.aspx">
                                Activity Log</a></li></ul>
                    </li>
                    <li><a title="Manage access control list" href="http://localhost:11139/administration/acl.aspx">
                        Access control list</a></li>
                    <li><a title="Manage All Settings" href="http://localhost:11139/administration/settings.aspx">
                        All Settings</a></li>
                    <li><a title="Manage SMS Providers" href="http://localhost:11139/administration/smsproviders.aspx">
                        SMS Providers</a></li>
                    <li><a title="Manage third-party integration" href="http://localhost:11139/administration/thirdpartyintegration.aspx">
                        Third-party Integration</a></li></ul>
            </li>
            <li><a title="System Home" href="http://localhost:11139/administration/systemhome.aspx">
                <img alt="System" src="/images/ico-system.png">
                System</a>
                <ul>
                    <li><a title="View Application Log" href="/user/adduser.mvc">
                        User</a>
													<ul>
														<li><a title="" href="/user/userlist.mvc">
                                User List</a></li>
                            <li><a title="" href="/user/useradd.mvc">
                                User Add</a></li></ul></li>
                    <li><a title="Manage Message Queue" href="http://localhost:11139/administration/messagequeue.aspx">
                        Message queue</a></li>
                    <li><a title="Maintenance" href="http://localhost:11139/administration/maintenance.aspx">
                        Maintenance</a></li>
                    <li><a title="Warnings" href="http://localhost:11139/administration/warnings.aspx">Warnings</a></li>
                    <li><a title="View System Information" href="http://localhost:11139/administration/systeminformation.aspx">
                        System Information</a></li></ul>
            </li>
            <li><a title="Help Home" href="http://localhost:11139/administration/helphome.aspx">
                <img alt="Help" src="/images/ico-help.png">
                Help</a>
                <ul>
                    <li><a title="View Help Topics" href="http://www.nopcommerce.com/documentation.aspx">
                        Help Topics</a></li>
                    <li><a title="Visit nopCommerce Community Forum" href="http://www.nopcommerce.com/boards/">
                        Community Forums</a></li></ul>
            </li>
        </ul>
    </div>
    <!--  <div class="status-bar">
        <div class="help">
        <%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>
        </div>
        <div class="breadcrumb">
            <span><a href="#">
                <img style="border-width: 0px;" alt="Skip Navigation Links" src="/images/WebResource.gif"
                width="0" height="0"></a><a id="ctl00_smp_SkipLink"></a></span></div>
		</div>-->
		<div class="clear">
		</div>
