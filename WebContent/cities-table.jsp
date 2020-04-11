<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 
</head>
<body>
 <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                   

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/cities-list" class="nav-link">Cities managements</a></li>
                    </ul>
                </nav>
            </header>
  <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">VietNam</h3>
                    <hr>
               <div class = "container-text">
               <a href = "<%=request.getContextPath() %>/newCityForm" class = "btn btn-success">Add new city</a>
               
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
                           
                            <c:forEach var="city" items="${cities}">

                                <tr>
                                    <td>
                                        <c:out value="${city.name}" />
                                      
                                    </td>
                                    <td>
                                        <c:out value="${city.totalOfInfected}" />
                                    </td>
                                    <td>
                                        <c:out value="${city.totalOfRecovered}" />
                                    </td>
                                    <td>
                                        <c:out value="${city.totalOfDeaths}" />
                                    </td>
                                     <td>
                                        <c:out value="${city.updatedInfected}" />
                                    </td>
                                     <td>
                                        <c:out value="${city.updatedRecovered}" />
                                    </td>
                                     <td>
                                        <c:out value="${city.updatedDeaths}" />
                                    </td>
                                    <td>
                                    <c:out value = "${city.getInfectedRate()}"/>
                                    </td>
                                    <td>
                                    <c:out value = "${city.getRecoveredRate()}"/>
                                    </td>
                                    <td>
                                    <c:out value = "${city.getDeathRate()}"/>
                                    </td>
                                    <td><a href="showCitiesUpdateForm?id=<c:out value='${city.id}'/>" ><i class="fa fa-pencil" aria-hidden="false"></i>Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteCity?id=<c:out value='${city.id}' />"><i class="fa fa-trash"></i>Delete</a></td>
                                </tr>
                            </c:forEach>
                           
                          
                        </tbody>

                    </table>
                    <div class = "container">
                    
                   <h2>VietNam</h2>
                     <table class = "table table-bordered">
                            
                            <thead>
                            <tr>
                            <th>Total Of Infected </th>
                            <th>Total Of Recovered</th>
                            <th>Total Of Deaths </th>
                              <th>New Infected </th>
                               <th>New Recovered </th>
                               <th>New Deaths</th>

                            
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            <td><c:out value = "${numberOfInfected}"/></td>
                            <td><c:out value = "${numberOfRecovered }"/></td>
                            <td><c:out value = "${numberOfDeaths }"/></td>
                             <td><c:out value = "${numberOfNewInfected }"/></td>
                              <td><c:out value = "${numberOfNewRecovered }"/></td>
                               <td><c:out value = "${numberOfNewDeaths }"/></td>
                            
                            </tr>
                            </tbody>
                            </table>
                             </div>
                </div>
            </div>
</body>
</html>