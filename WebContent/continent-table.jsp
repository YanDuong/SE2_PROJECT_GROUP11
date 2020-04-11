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
<body class = "container">
<header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: green">
                   

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/showContinentList" class="nav-link">Continents Management</a></li>
                    </ul>
                </nav>
            </header>
<h2>Continents</h2>
<table class = "table table-bordered">
<thead>
<tr>
<th>Name</th>
<th>Number Of Infected</th>
<th>Number Of Recovered</th>
<th>Number Of Deaths</th>
<th>New Infected </th>
<th>New Recovered</th>
<th>New Deaths</th>
 <th>Rate of Infected</th>
<th>Rate of Recovered</th>
 <th>Rate of Deaths</th>
<th>Actions</th>

</tr>
</thead>
<tbody>
<c:forEach var = "continent" items = "${continents}">
<tr>
<td><c:out value = "${continent.name }"/></td>
<td><c:out value = "${continent.totalOfInfected}"/></td>
<td><c:out value = "${continent.totalOfRecovered}"/></td>
<td><c:out value = "${continent.totalOfDeaths}"/></td>
<td><c:out value = "${continent.updatedInfected }"/></td>
<td><c:out value = "${continent.updatedRecovered }"/></td>
<td><c:out value = "${continent.updatedDeaths }"/></td>
 <td>
                                    <c:out value = "${continent.getInfectedRate()}"/>
                                    </td>
                                    <td>
                                    <c:out value = "${continent.getRecoveredRate()}"/>
                                    </td>
                                    <td>
                                    <c:out value = "${continent.getDeathRate()}"/>
                                    </td>
 <td><a href="showContinentUpdateForm?id=<c:out value='${continent.id}'/>" ><i class="fa fa-pencil" aria-hidden="false"></i>Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; </td>
</tr>
</c:forEach>
</tbody>

</table>


 <div class = "container">
                    
                   <h2>The world</h2>
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
                            <td><c:out value = "${worldInfected}"/></td>
                            <td><c:out value = "${worldRecovered }"/></td>
                            <td><c:out value = "${worldDeaths }"/></td>
                             <td><c:out value = "${newInfected }"/></td>
                              <td><c:out value = "${newRecovered }"/></td>
                               <td><c:out value = "${newDeaths }"/></td>
                               
                            
                            </tr>
                            </tbody>
                            </table>
                             </div>

</body>
</html>