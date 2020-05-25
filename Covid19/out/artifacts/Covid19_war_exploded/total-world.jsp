<%@ page import="net.se2project.covidtracker.model.Country" %>
<%@ page import="net.se2project.covidtracker.dao.CountryDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>World</title>
    <style>
        .feature_item p{
            text-align: center;
            font-weight: bold;
        }
        #hid ,#hid1 ,#hid2, #hid3{
            visibility: hidden;
        }
    </style>
</head>
<body>
    <%
        CountryDAO total = null;
        try {
            total = new CountryDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert total != null;
        List<Country> tol = total.listTotal();
        if (tol != null) {
            for (Country c : tol) {%>
        <!--================End Feature Area =================-->
        <section class="feature_area p_120">
            <div class="container">
                <div class="main_title">
                    <br>
                    <br>
                    <br>
                    <br>
                    <h2>TOTAL COVID-19</h2>
                    <br>
                </div>
                <div class="feature_inner row">

                    <div class="col-lg-4 col-md-6">
                        <div class="feature_item" >
                            <i class="flaticon-skyline"></i>
                            <h4 style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "<%=c.getTotal_death()%>" /></h4>
                            <p>(Deaths)</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="feature_item">
                            <i class="flaticon-city"></i>
                            <h4><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "<%=c.getTotal_cases()%>" /> </h4>
                            <p>(Coronavirus Cases)</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="feature_item">
                            <i class="flaticon-sketch"></i>
                            <h4 style="color: green"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "<%=c.getTotal_recovered()%>" /></h4>
                            <p>(Recovered)</p>
                        </div>
                    </div>
                </div>
                <br>
                <br>

                <div class="feature_inner row">

                    <div class="col-lg-3 col-md-6">
                        <div class="feature_item" id = "hid3">
                            <i class="flaticon-city"></i>
                            <h4></h4>
                            <p>(Critical Cases)</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="feature_item">
                            <i class="flaticon-city"></i>
                            <h4 style="color: #0000FF"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "<%=c.getActive_cases()%>" /> </h4>
                            <p>(Active Cases)</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="feature_item">
                            <i class="flaticon-city"></i>
                            <h4 style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "<%=c.getCritical_cases()%>" /> </h4>
                            <p>(Critical Cases)</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="feature_item" id = "hid1">
                            <i class="flaticon-city"></i>
                            <h4></h4>
                            <p>(Critical Cases)</p>
                        </div>
                    </div>
                </div>
                <br>
                <br>
            </div>
        </section>
        <%
            }
        } else {%>
        There has been an error and the comment list is empty.
        <%
            }
        %>
        <!--================End Feature Area =================-->
<br>
<br>
<br>
</body>
</html>
