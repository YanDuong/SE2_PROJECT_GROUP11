<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cities Management</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: green">
              
                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/CitySerlvet/cities-list" class="nav-link">Cities</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${city != null}">
                            <form action="updateCityStatistics" method="post">
                        </c:if>
                        <c:if test="${city == null}">
                            <form action="addNewCity" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${city != null}" >
                                   Edit city statistics
                                </c:if>
                                <c:if test="${city == null}" >
                                   Add new city
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${city != null}">
                            <input type="hidden" name="id" value="<c:out value='${city.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label> Name</label> <input type="text" value="<c:out value='${city.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Number of Infected</label> <input type="text" value="<c:out value='${city.totalOfInfected}' />" class="form-control" name="totalOfInfected">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Number of Recovered </label> <input type="text" value="<c:out value='${city.totalOfRecovered}' />" class="form-control" name="totalOfRecovered">
                        </fieldset>
                        
                         <fieldset class="form-group">
                            <label>Number of Deaths </label> <input type="text" value="<c:out value='${city.totalOfDeaths}' />" class="form-control" name="totalOfDeaths">
                        </fieldset>
                        
                         <fieldset class="form-group">
                            <label>New Infected </label> <input type="text" value="<c:out value='${city.updatedInfected}' />" class="form-control" name="updatedInfected">
                        </fieldset>
                        
                         <fieldset class="form-group">
                            <label>New Recovered</label> <input type="text" value="<c:out value='${city.updatedRecovered}' />" class="form-control" name="updatedRecovered">
                        </fieldset>
                        
                         <fieldset class="form-group">
                            <label>New Deaths </label> <input type="text" value="<c:out value='${city.updatedDeaths}' />" class="form-control" name="updatedDeaths">
                        </fieldset>
                      
                        
                        
                        
                        
                        
                        

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
</body>
</html>