<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link href="<c:url value="css/scroll_table.scss" />" rel="stylesheet">
</head>
<body>

 <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: green">
                   

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/showCountryList" class="nav-link">Corona Statistics</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">Countries</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath() %>/newCountryForm" class="btn btn-success">Add
     New Country</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                               
                                <th>Name</th>
                                <th>Number of Infected</th>
                                <th>Number of Recovered</th>
                                <th>Number of Deaths</th>
                                 <th>New Infected</th>
                                  <th>New Recovered</th>
                                   <th>New Deaths</th>
                                   
                                    <th>Rate of Infected</th>
                                    <th>Rate of Recovered</th>
                                     <th>Rate of Deaths</th>
<th>Actions</th>                                   
                            </tr>
                        </thead>
                        <tbody>
                           
                            <c:forEach var="country" items="${countries}">

                                <tr>
                                    <td>
                                        <c:out value="${country.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${country.totalOfInfected}" />
                                    </td>
                                    <td>
                                        <c:out value="${country.totalOfRecovered}" />
                                    </td>
                                    <td>
                                        <c:out value="${country.totalOfDeaths}" />
                                    </td>
                                     <td>
                                        <c:out value="${country.updatedInfected}" />
                                    </td>
                                     <td>
                                        <c:out value="${country.updatedRecovered}" />
                                    </td>
                                     <td>
                                        <c:out value="${country.updatedDeaths}" />
                                    </td>
                                    <td>
                                    <c:out value = "${country.getInfectedRate()}"/>
                                    </td>
                                    <td>
                                    <c:out value = "${country.getRecoveredRate()}"/>
                                    </td>
                                    <td>
                                    <c:out value = "${country.getDeathRate()}"/>
                                    </td>
                                    <td><a href="showUpdateForm?id=<c:out value='${country.id}'/>" ><i class="fa fa-pencil" aria-hidden="false"></i>Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteCountry?id=<c:out value='${country.id}' />"><i class="fa fa-trash"></i>Delete</a></td>
                                </tr>
                            </c:forEach>
                          
                        </tbody>

                    </table>
                </div>
            </div>
            
          

</body>
</html>