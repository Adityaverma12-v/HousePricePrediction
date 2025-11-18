<%@page import="com.uity.model.Regression"%>
<%@page import="com.uity.model.RegressionAnalysis"%>
<%@ page language="java" pageEncoding="utf8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="./css/bootstrap.css" />" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Hello Spring Web MVC</title>
<spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<style type="text/css">
.card-body {
text-align: center;
}
</style>
</head>
<body>

<% 
	RegressionAnalysis ra = new RegressionAnalysis();
	double result = ra.RA();
	%>

    <div class="container">
    
    <div class="text-center py-3">
    		<br>
			<br>
			<h3>Housing Guide: Predicting Housing Prices</h3>
			<br>
			<br>
    
    </div>
 			
 
  <div class="card bg-primary text-white">
    <div class="card-body">
    <p>Now, Coefficient of Determination R squared for the dataset of house price prediction is </p>
    <%=result %>
    
    <p><br> It is better when R squared approaches to 1.</p>
    </div>
  </div>
  
</div>

<footer>
	<br>
	<br>
	<br>
	<br>
	<p align="center">copyright © Aye Thant May UIT 2018</p>

</footer>

</body>