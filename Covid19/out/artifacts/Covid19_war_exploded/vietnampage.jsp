<%@ page import="net.se2project.covidtracker.model.Vietnam" %>
<%@ page import="net.se2project.covidtracker.dao.VietnamDAO" %>
<%@ page import="net.se2project.covidtracker.dao.CountryDAO" %>

<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">


<script src="https://kit.fontawesome.com/yourcode.js"></script>

<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"  type="text/css" href="assets/css/style.css">
<jsp:include page="nav-bar-vn.jsp"></jsp:include>
<jsp:include page="totalVietnam.jsp"></jsp:include>
<html>
<head>
    <title>Vietnam</title>
</head>
<body>

<ul>
    <li>
        <span></span>
        <span></span>
        <span></span>
    </li>
</ul>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of All Province</h3>
        <div class="container text-left">
            <a href="province-form.jsp" class="btn btn-success" >Add New Province</a>
        </div>
        <br>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Province</th>
                <th>Total Cases</th>
                <th>Total Death</th>
                <th>Total Recovered</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="provinces" items="${listProvince}">
                <tr>
                    <td id = "total"><c:out value="${provinces.province_name}" /></td>
                    <td style="text-align: center"><c:out value="${provinces.total_cases}" /></td>
                    <td style="text-align: center"><c:out value="${provinces.active_cases}" /></td>
                    <td style="text-align: center"><c:out value="${provinces.total_recovered}" /></td>
                    <td style="text-align: center"><c:out value="${provinces.total_death}" /></td>

                    <td>
                        <a href="EditProvinceServlet?id=<c:out value='${provinces.id}'/>"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i> </a>
                        <a href="DeleteProvinceServlet?id=<c:out value='${provinces.id}' />"> <i class="fa fa-trash-o fa-fw"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
