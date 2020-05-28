<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Country Form</title>
	<jsp:include page="navbar.jsp"></jsp:include>
</head>
<body>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<%--@elvariable id="country" type=""--%>
				<c:if test="${country != null}">
					<form action="UpdateCountryServlet" method="post" name="myForm">
				</c:if>
				<c:if test="${country == null}">
					<form action="AddCountryServlet" method="post" name="myForm">
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
					<label>Total Cases</label> <input type="number"
						value="<c:out value='${country.total_cases}' />" class="form-control"
						name="total_cases" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>New Cases</label> <input type="number"
						value="<c:out value='${country.new_cases}' />" class="form-control"
						name="new_cases" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Total Death</label> <input type="number"
													value="<c:out value='${country.total_death}' />" class="form-control"
													name="total_death" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>New Death</label> <input type="number"
													value="<c:out value='${country.new_death}' />" class="form-control"
													name="new_death" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Total Recovered</label> <input type="number"
													value="<c:out value='${country.total_recovered}' />" class="form-control"
													name="total_recovered" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Active Cases</label> <input type="number"
													value="<c:out value='${country.active_cases}' />" class="form-control"
													name="active_cases" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Critical Cases</label> <input type="number"
													   value="<c:out value='${country.critical_cases}' />" class="form-control"
													   name="critical_cases" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success" onclick="return confirm('Are you sure you want to save changes?');">Save</button>
				<button class="btn btn-danger" onclick="window.location.href='/ShowAllCountryServlet'">Cancel</button>
					</form>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
<%--	<script type="text/javascript" src="assets/js/search.js"></script>--%>

</body>
</html>
