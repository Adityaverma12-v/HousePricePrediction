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

<title>Input Form</title>

<script type="text/javascript" src="./js/validate.js"></script>

<spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />

<style>
.center-form .row {
	justify-content: center !important;
}
</style>

</head>

<body>
	<div class="container">
	
		<div class="text-center py-3">
			<br>
			<br>
			<h3>Housing Guide: Predicting Housing Prices</h3>
			<br>
			<br>
		</div>
		
		<form class="center-form" method="POST" action="output.do" name="form2">
		
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Number of bedrooms</label> 
				<input type="number" class="form-control col-sm-4" name="bed" placeholder="0 - 10" min="0" max="10" required>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Number of bathrooms</label> 
				<input type="number" class="form-control col-sm-4" name="bath" placeholder="0 - 10" min="0" max="10" required>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Number of floor</label> 
				<input type="number" class="form-control col-sm-4" name="floor" placeholder="1 - 4" min="1" max="4" required>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Year Built</label> 
				<input type="number" class="form-control col-sm-4" name="year" placeholder="1900 - 2015" min="1900" max="2015" required>
			</div>
			
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Zipcode</label> 
				<input type="number" class="form-control col-sm-4" name="zip" placeholder="98001 - 98200" min="98001" max="98200" required>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Square Feet of Living Room</label> 
				<input type="number" class="form-control col-sm-4" name="living" placeholder="1 - 14000" min="1" max="14000" required>
			</div>
			
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Square Feet of Lot</label> 
				<input type="number" class="form-control col-sm-4" name="lot" placeholder="1 - 2000000" min="1" max="2000000" required>
			</div>
			
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Square Feet of Above</label>
				<input type="number" class="form-control col-sm-4" name="above" placeholder="1 - 10000" min="1" max="10000" required>
			</div>
			
			
			<div class="form-group row">
				<label class="col-sm-4 col-form-label">Square Feet of Basement</label> 
				<input type="number" class="form-control col-sm-4" name="base" placeholder="0 - 5000" min="0" max="5000" required>
			</div>
			
			<br>
			<br>
			
			<div class="form-group row">
			
				<input type="submit" class="btn btn-primary mx-1" value="Predict"> 
				<input type="reset" class="btn btn-secondary mx-1" value="Cancel">
				
			</div>
					
		</form>
		<br>
		<br>

	</div>
</body>

<footer>
	<br>
	<p align="center">copyright © Aditya Verma 2025</p>

</footer>

</html>


