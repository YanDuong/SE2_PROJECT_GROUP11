<%@ page import="net.se2project.covidtracker.model.Country" %>
<%@ page import="net.se2project.covidtracker.dao.CountryDAO" %>
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
    <jsp:include page="navbar.jsp"></jsp:include>
    <jsp:include page="total.jsp"></jsp:include>
    <script>
        var toggle = function (elem) {
            elem.classList.toggle('is-visible');
        };

        document.addEventListener('click', function (event) {

            if (!event.target.classList.contains('toggle')) return;
            event.preventDefault();
            var content = document.querySelector(event.target.hash);
            if (!content) return;
            toggle(content);

        }, false);


        var modal = document.getElementById('id02');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }


    </script>
</head>
<body>

    <div class="row">
        <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

        <div class="container">
            <h3 class="text-center">List of All Country</h3>
            <div class="container text-left">
            <a href="country-form.jsp" class="btn btn-success" >Add New Country</a>
            </div>
            <br>

            <nav class="navbar navbar-expand-md navbar-dark" id="navbar">
                <a class="navbar-brand" href="index.jsp"><img src="images/loogoo.png" style="height:50px;"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="collapsibleNavbar">
                    <ul class="nav navbar-nav flex-fill w-100 flex-nowrap">
                        <li class="nav-item">
                            <a class="nav-link" href="/getAllArticles">Article Gallery</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/gallery">Media Gallery</a>
                        </li>

                    </ul>
                    <ul class="navbar-nav ml-auto w-100 justify-content-center">
                        <li>
                            <form class="form-inline" action="SearchArticle" id="searchField">
                                <div class="input-group" id="searchbox">
                                    <input type="text" name="info" class="form-control" placeholder="Search articles">
                                </div>
                            </form>
                        </li>
                    </ul>
                    <ul class="navbar-nav ml-auto w-100 justify-content-end">
                        <li class="nav-item">
                            <a class="nav-link" onclick="document.getElementById('id02').style.display='block'"> Login</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div id="id02" class="modal">
                <span onclick="document.getElementById('id02').style.display='none'" class="close"
                      title="Close Modal">&times;</span>
            </div>


            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Country</th>
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
                <c:forEach var="country" items="${listCountry}">
                    <tr>
                        <td id = "total"><c:out value="${country.country_name}" /></td>
                        <td style="text-align: right"><c:out value="${country.total_cases}" /></td>
                        <td style="color: red"><c:out value="${country.new_cases}" /></td>
                        <td style="color: red"><c:out value="${country.total_death}" /></td>
                        <td style="color: red"><c:out value="${country.new_death}" /></td>
                        <td style="color: green"><c:out value="${country.total_recovered}" /></td>
                        <td style="color: blue"><c:out value="${country.active_cases}" /></td>
                        <td style="color: red"><c:out value="${country.critical_cases}" /></td>

                        <td>
                        <a  href="EditCountryServlet?id=<c:out value='${country.id}'/>"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i> </a>
                        <a href="DeleteCountryServlet?id=<c:out value='${country.id}' />"> <i class="fa fa-trash-o fa-fw"></i></a></td>
                    </tr>
                </c:forEach>
                <!-- } -->
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
