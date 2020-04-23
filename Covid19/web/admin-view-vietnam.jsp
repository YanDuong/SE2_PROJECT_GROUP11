<%@ page import="net.se2project.covidtracker.model.Vietnam" %>
<%@ page import="net.se2project.covidtracker.dao.VietnamDAO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Covid Tracker</title>

    <script src="https://kit.fontawesome.com/yourcode.js"></script>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet"  type="text/css" href="assets/css/style.css">
    <jsp:include page="nav-bar-vn.jsp"></jsp:include>
    <jsp:include page="totalVietnam.jsp"></jsp:include>

</head>
<body>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center">List of All Country</h3>
        <div class="container text-left">
            <a href="province-form.jsp" class="btn btn-success" >Add New Country</a>
        </div>
        <br>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Country</th>
                <th>Total Cases</th>
                <th>Active Cases</th>
                <th>Total Recovered</th>
                <th>Total Death</th>
            </tr>
            </thead>
            <tbody>

            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="province" items="${listProvince}">
                <tr>
                    <td id = "total"><c:out value="${province.country_name}" /></td>
                    <td><c:out value="${province.total_cases}" /></td>
                    <td style="color: blue"><c:out value="${province.active_cases}" /></td>
                    <td style="color: green"><c:out value="${province.total_recovered}" /></td>
                    <td style="color: red"><c:out value="${province.total_death}" /></td>
                    <td>
                        <a  href="EditProvinceServlet?id=<c:out value='${province.id}'/>"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i> </a>
                        <a href="DeleteProvinceServlet?id=<c:out value='${province.id}' />"> <i class="fa fa-trash-o fa-fw"></i></a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>
        </table>
    </div>
</div>


</body>
</html>
