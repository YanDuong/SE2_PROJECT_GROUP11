<%@ page import="net.se2project.covidtracker.dao.CountryDAO" %>
<%@ page import="net.se2project.covidtracker.model.Country" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="service.CountryService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Covid Tracker</title>
    <jsp:include page="navbar.jsp"></jsp:include>
    <jsp:include page="total-world.jsp"></jsp:include>
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
                <jsp:useBean id="listContinent" scope="request" type="java.util.List"/>
                <c:forEach items="${listContinent}" var="continent">
                    <a class="dropdown-item mdb-dropdownLink-2" href="ShowChartContinentServlet?id=<c:out value='${continent.id}'/>">${continent.country_name}</a>
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
    <div class="container">
        <h3 class="text-center">List of All Country</h3>

        <%if (session.getAttribute("admin") != null){%>
        <div class="container text-left">
            <a href="form-continent.jsp" class="btn btn-success" >Add New Continent</a>
            <a href="${pageContext.request.contextPath}/AutoUpdateContinentServlet" class="btn btn-success" >Auto Update All</a>
            <a href="${pageContext.request.contextPath}/DeleteAllContinentServlet" class="btn btn-success" >Delete All</a>
        </div>
        <%}%>

        <br>

        <form class="form-inline d-flex justify-content-center md-form form-sm active-cyan-2 mt-2">
            <input id="myInput" class="form-control form-control-sm mr-3 w-75" onkeyup="myFunction()" type="text" placeholder="Search Country"
                   aria-label="Search">
            <i class="fas fa-search" aria-hidden="true"></i>
        </form>

        <table class="table table-striped" id="myTable">
            <thead>
            <tr class="header">
                <th>Continent</th>
                <th>Total Cases</th>
                <th>New Cases</th>
                <th>Total Death</th>
                <th>New Death</th>
                <th>Total Recovered</th>
                <th>Active Cases</th>
                <th>Critical</th>
            </tr>
            </thead>
            <tbody>

            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="continent" items="${listContinent}">
                <tr>
                    <td id = "total"><c:out value="${continent.country_name}" /></td>
                    <td ><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${continent.total_cases}" /></td>
                    <td style="color: rgb(239,136,57)"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${continent.new_cases}" /></td>
                    <td style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${continent.total_death}" /></td>
                    <td style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${continent.new_death}" /></td>
                    <td style="color: green"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${continent.total_recovered}" /></td>
                    <td style="color: blue"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${continent.active_cases}" /></td>
                    <td style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${continent.critical_cases}" /></td>

                    <%if (session.getAttribute("admin") != null){%>
                    <td>
                        <a href="EditContinentServlet?id=<c:out value='${continent.id}'/>"><i class="fas fa-pencil-alt"></i> &nbsp;&nbsp;</a>
                        <a href="DeleteContinentServlet?id=<c:out value='${continent.id}' />" onclick="return confirm('Are you sure you want to delete ${continent.country_name}?');"> <i class="fas fa-trash"></i></a>
                    </td>
                    <%}%>

                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>
        </table>
    </div>
</div>
<br>

<button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fas fa-chevron-up"></i></button>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="/ShowAllWorldChartServlet"></jsp:include>

<script type="text/javascript" src="assets/js/search.js"></script>

</body>

</html>

<script>
    <%
        CountryService total = null;try {
        total = new CountryService();} catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert total != null;
        List<Country> tol = null;try {
        tol = total.listTotal();} catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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