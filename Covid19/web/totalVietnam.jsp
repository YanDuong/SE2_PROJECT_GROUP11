
<%@ page import="java.util.List" %>
<%@ page import="net.se2project.covidtracker.dao.VietnamDAO" %>
<%@ page import="net.se2project.covidtracker.model.Vietnam" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://kit.fontawesome.com/yourcode.js"></script>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet"  type="text/css" href="assets/css/styleTotal.css">

    <style>
        .feature_item p{
            text-align: center;
            font-weight: bold;
        }
         #hid3, #hid4{
            visibility: hidden;
        }
    </style>
</head>
<body>
<%
    VietnamDAO total = new VietnamDAO();
    List<Vietnam> tol = total.listProvinceTotal();
    if (tol != null) {
        for (Vietnam c : tol) {%>
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
                    <h4 style="color: red"><%=c.getTotal_death()%></h4>
                    <p>(Deaths)</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="feature_item">
                    <i class="flaticon-city"></i>
                    <h4><%=c.getTotal_cases()%> </h4>
                    <p>(Coronavirus Cases)</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="feature_item">
                    <i class="flaticon-sketch"></i>
                    <h4 style="color: green"><%=c.getTotal_recovered()%></h4>
                    <p>(Recovered)</p>
                </div>
            </div>
        </div>
        <br>
        <br>

        <div class="feature_inner row">

            <div class="col-lg-4 col-md-6">
                <div class="feature_item" id = "hid3">
                    <i class="flaticon-city"></i>
                    <h4></h4>
                    <p>(Critical Cases)</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="feature_item">
                    <i class="flaticon-city"></i>
                    <h4 style="color: #0000FF"><%=c.getActive_cases()%> </h4>
                    <p>(Active Cases)</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="feature_item" id = "hid4">
                    <i class="flaticon-city"></i>
                    <h4 style="color: red"> </h4>
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
