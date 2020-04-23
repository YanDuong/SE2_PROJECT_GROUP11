<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Country Form</title>
	<script src="https://kit.fontawesome.com/yourcode.js"></script>

	<link rel="stylesheet"
		  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		  crossorigin="anonymous">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Covid Tracker</title>

	<script
			src="https://code.jquery.com/jquery-3.4.1.min.js"
			integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
			crossorigin="anonymous"></script>

	<link rel="stylesheet"  type="text/css" href="assets/css/style.css">

</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#CountryForm").validate({
			rules: {
				country_name: "required",
				total_cases: "required",
				new_cases: "required",
				total_death: "required",
				new_death: "required",
				total_recovered: "required",
				active_cases: "required",
				critical_cases: "required",

			},

			messages: {
				country_name: "Please enter country_name",
				total_cases: "Please enter total_cases",
				new_cases: "Please enter new_cases",
				total_death: "Please enter total_death",
				new_death: "Please enter new_death",
				total_recovered: "Please enter total_recovered",
				active_cases: "Please enter active_cases",
				critical_cases: "Please enter critical_cases",
			}
		});

	});
</script>
	<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
		<div class="container">
			<div class="navbar-header">
				<a href="<%=request.getContextPath()%>/ShowAllCountryServlet" class="navbar-brand">Home</a>
			</div>
		</div>
	</header>

	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${country != null}">
					<form action="UpdateCountryServlet" method="post">
				</c:if>
				<c:if test="${country == null}">
					<form action="AddCountryServlet" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${country != null}">
            			Edit country
            		</c:if>
						<c:if test="${country == null}">
            			Add New country
            		</c:if>
					</h2>
				</caption>

				<c:if test="${country != null}">
					<input type="hidden" name="id" value="<c:out value='${country.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Country Name</label> <input type="text"
						value="<c:out value='${country.country_name}' />" class="form-control"
						name="country_name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Total Cases</label> <input type="text"
						value="<c:out value='${country.total_cases}' />" class="form-control"
						name="total_cases">
				</fieldset>

				<fieldset class="form-group">
					<label>New Cases</label> <input type="text"
						value="<c:out value='${country.new_cases}' />" class="form-control"
						name="new_cases">
				</fieldset>

				<fieldset class="form-group">
					<label>Total Death</label> <input type="text"
													value="<c:out value='${country.total_death}' />" class="form-control"
													name="total_death">
				</fieldset>
				<fieldset class="form-group">
					<label>New Death</label> <input type="text"
													value="<c:out value='${country.new_death}' />" class="form-control"
													name="new_death">
				</fieldset>
				<fieldset class="form-group">
					<label>Total Recovered</label> <input type="text"
													value="<c:out value='${country.total_recovered}' />" class="form-control"
													name="total_recovered">
				</fieldset>
				<fieldset class="form-group">
					<label>Active Cases</label> <input type="text"
													value="<c:out value='${country.active_cases}' />" class="form-control"
													name="active_cases">
				</fieldset>
				<fieldset class="form-group">
					<label>Critical Cases</label> <input type="text"
													   value="<c:out value='${country.critical_cases}' />" class="form-control"
													   name="critical_cases">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
					</form>
					<a style="color:black" href="<%=request.getContextPath()%>/ShowAllCountryServlet" >Cancel</a>

			</div>
		</div>
	</div>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>
