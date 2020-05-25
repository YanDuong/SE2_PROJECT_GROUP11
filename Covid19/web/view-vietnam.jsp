<%@ page import="java.util.List" %>
<%@ page import="net.se2project.covidtracker.dao.CountryDAO" %>
<%@ page import="net.se2project.covidtracker.model.Country" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Covid Tracker</title>
    <jsp:include page="navbar.jsp"></jsp:include>
    <jsp:include page="total-vietnam.jsp"></jsp:include>
</head>
<body>
<div class="dropdown">
    <div class="col text-center">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdown_coins" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">
            Show Chart
        </button>
        <div id="menu" class="dropdown-menu" aria-labelledby="dropdown_coins">
            <div id="menuItems">
                <jsp:useBean id="listProvince" scope="request" type="java.util.List"/>
                <c:forEach items="${listProvince}" var="province">
                    <a class="dropdown-item mdb-dropdownLink-2" href="ShowChartProvinceServlet?id=<c:out value='${province.id}'/>">${province.country_name}</a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <canvas id="myChart"></canvas>
        </div>
        <div class="col-md-6">
            <canvas id="pieChart"></canvas>
        </div>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col-md-12">
            <canvas id="lineChart"></canvas>
        </div>
    </div>
</div>
<hr>
<br>
<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center">List of All Country</h3>

        <%if (session.getAttribute("admin") != null){%>
        <div class="container text-left">
            <a href="form-city.jsp" class="btn btn-success" >Add New City</a>
            <a href="${pageContext.request.contextPath}/AutoUpdateProvinceServlet" class="btn btn-success" >Auto Update Province</a>
            <a href="${pageContext.request.contextPath}/AutoUpdateChartServlet" class="btn btn-success" >Auto Update Chart Vietnam</a>

        </div>
        <%}%>
        <br>
        <form class="form-inline d-flex justify-content-center md-form form-sm active-cyan-2 mt-2">
            <input type="text" id="myInput" class="form-control form-control-sm mr-3 w-75" onkeyup="myFunction()" placeholder="Search City"
                   aria-label="Search">
            <i class="fas fa-search" aria-hidden="true"></i>
        </form>

        <table class="table table-striped" id="myTable">
            <thead>
            <tr class="header">
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
                    <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${province.total_cases}" /></td>
                    <td style="color: blue"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${province.active_cases}" /></td>
                    <td style="color: green"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${province.total_recovered}" /></td>
                    <td style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${province.total_death}" /></td>

                    <%if (session.getAttribute("admin") != null){%>
                    <td>
                        <a  href="EditProvinceServlet?id=<c:out value='${province.id}'/>"><i class="fas fa-pencil-alt"></i> &nbsp;&nbsp;</a>
                        <a onclick="return confirm('Are you sure you want to delete this item?');" href="DeleteProvinceServlet?id=<c:out value='${province.id}'  />" > <i class="fas fa-trash"></i></a>
                    </td>
                    <%}%>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>
        </table>
    </div>
</div>

<button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fas fa-chevron-up"></i></button>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="/ShowAllVietnamChartServlet"></jsp:include>
<script type="text/javascript" src="assets/js/search.js"></script>

</body>
</html>

<script>
    <%
        CountryDAO total = null;
        try {
        total = new CountryDAO();} catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert total != null;
        List<Country> tol = total.listTotalProvince();
        if (tol != null) {
        for (Country c : tol) {%>

    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Active", "Recovered", "Death"],
            datasets: [{
                label: '',
                data: [
                    <%=c.getActive_cases()%>,
                    <%=c.getTotal_recovered()%>,
                    <%=c.getTotal_death()%>
                ],
                backgroundColor: [
                    'rgba(255, 206, 86, 1)',
                    '#46BFBD',
                    'rgba(255,99,132,1)'
                ],
                borderColor: [
                    'rgba(255, 206, 86, 1)',
                    '#46BFBD',
                    'rgba(255,99,132,1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    var ctxP = document.getElementById("pieChart").getContext('2d');
    var myPieChart = new Chart(ctxP, {
        type: 'pie',
        data: {
            labels: ["Death", "Recovered", "Active Cases"],
            datasets: [{
                data: [
                    <%=c.getTotal_death()%>,
                    <%=c.getTotal_recovered()%>,
                    <%=c.getActive_cases()%>,
                ],
                backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870"]
            }]
        },
        options: {
            responsive: true
        }
    });


    <%}
}%>

    //line
    var ctxL = document.getElementById("lineChart").getContext('2d');
    var myLineChart = new Chart(ctxL, {
        type: 'line',
        data: {
            labels: [
                <jsp:useBean id="listChart" scope="request" type="java.util.List"/>
                <c:forEach var="chart" items="${listChart}" varStatus="loop">
                    "${chart.date}"${!loop.last ? ',' : ''}
                </c:forEach>
                ],
            datasets: [{
                label: "News Cases",
                data: [
                <c:forEach var="chart" items="${listChart}" varStatus="loop">
                    ${chart.cases}${!loop.last ? ',' : ''}
                </c:forEach>
                ],
                backgroundColor: [
                    'rgba(92,0,132,0.24)',
                ],
                borderColor: [
                    'rgba(246,0,79,0.7)',
                ],
                borderWidth: 2
            },
            ]
        },
        options: {
            responsive: true
        }
    });

</script>
