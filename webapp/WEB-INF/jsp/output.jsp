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
</head>

<body>

<%  String living = request.getParameter("living");
	String lot = request.getParameter("lot");
	String above = request.getParameter("above");
	String base = request.getParameter("base");
	String bed =  request.getParameter("bed");
	String bath =  request.getParameter("bath");
	String floor =  request.getParameter("floor");
	String year =  request.getParameter("year");
	String zip =  request.getParameter("zip");
	
	Double price;
	
	Regression r = new Regression();
	Double[] d=r.Reg();
	price= d[9]*-1+d[0]*-1*Double.parseDouble(bed)+d[1]*Double.parseDouble(bath)+d[2]*Double.parseDouble(floor)+d[3]*-1*Double.parseDouble(year)+d[4]*Double.parseDouble(zip)+d[5]*Double.parseDouble(living)+d[6]*-1*Double.parseDouble(lot)+d[7]*Double.parseDouble(above)+d[8]*Double.parseDouble(base);
%>

	<div class="container">
		<div class="text-center">
			<br>
			<br>
			<h3>Housing Guide: Predicting Housing Prices</h3>
			<br>
			<br>
		</div>
		<div class="row">
			<p>You want a house with following specifications.</p>
			<table class="table table-bordered table-hover">
				<thead style="background: #3179c6; font-size:18px ; color: white;">
					<tr>
						<th scope="col">House Specifications</th>
						<th scope="col">Measurements and Values</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Number of bedrooms</th>
						<td><%=bed %></td>
					</tr>
					<tr>
						<th>Number of bathrooms</th>
						<td><%=bath %></td>
					</tr>
					<tr>
						<th>Number of floors</th>
						<td><%=floor %></td>
					</tr>

					<tr>
						<th>Year Built</th>
						<td><%=year %></td>
					</tr>
					<tr>
						<th>Zipcode</th>
						<td><%=zip %></td>
					</tr>

					<tr>
						<th>Living Room Square Feet</th>
						<td><%=living  %></td>
					</tr>
					<tr>
						<th>Lot Square Feet</th>
						<td><%=lot  %></td>
					</tr>
					<tr>
						<th>Above Square feet</th>
						<td><%=above  %></td>
					</tr>
					<tr>
						<th>Basement Square Feet</th>
						<td><%=base  %></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<br>
		<div class="row">
			<br>
			<p>Price of the house shall be  </p> ( <p style="font-style: italic; font-family: 'Times New Roman', Times, serif; font-size: 18px; font-weight: bold;"><%=price  %></p> ) 
			<p>   in USD.</p>
			
			<!--  
			<p> d[9] is <%=d[9]%></p><br/>
			<p> d[0] is <%=d[0]%></p><br/>
			<p> d[1] is <%=d[1]%></p><br/>
			<p> d[2] is <%=d[2]%></p><br/>
			<p> d[3] is <%=d[3]%></p><br/>
			<p> d[4] is <%=d[4]%></p><br/>
			<p> d[5] is <%=d[5]%></p><br/>
			<p> d[6] is <%=d[6]%></p><br/>
			<p> d[7] is <%=d[7]%></p><br/>
			<p> d[8] is <%=d[8]%></p><br/>
			-->
			
		</div>
	</div>
	<br>
	<br>

	<footer>
		<br>
		
		<form method="POST" action="evaluate.do" id="myForm">
		<p align="center"> 
		<a  style="color: blue; text-decoration: underline; cursor:pointer;" onclick="document.getElementById('myForm').submit()">Check accuracy for the existing dataset.</a> <br>
		copyright © Aye Thant May UIT 2018</p>
		</form>
		
		<br>
	</footer>

</body>

<!--  
<script type="text/javascript">
function rsq()
{
	<% 
	RegressionAnalysis ra = new RegressionAnalysis();
	double result = ra.RA();
	%>
	alert ("Coefficient of Determination R squared for the dataset of house price prediction is    " + <%=result %> + " .   It is better when R squared approaches to 1.");
	}
</script>
-->

</html>

