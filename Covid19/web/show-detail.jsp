<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Covid Tracker</title>
    <jsp:include page="navbar.jsp"></jsp:include>
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
<section class="feature_area p_120">
    <div class="container">
        <div class="main_title">
            <br>
            <br>
            <br>
            <br>
            <h2>
            <c:out value='${country.country_name}' /></h2>
            <br>
        </div>
        <div class="feature_inner row">

            <div class="col-lg-4 col-md-6">
                <div class="feature_item" >
                    <i class="flaticon-skyline"></i>
                    <h4 style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${country.total_death}" /></h4>
                    <p>(Deaths)</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="feature_item">
                    <i class="flaticon-city"></i>
                    <h4><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${country.total_cases}" /> </h4>
                    <p>(Coronavirus Cases)</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="feature_item">
                    <i class="flaticon-sketch"></i>
                    <h4 style="color: green"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${country.total_recovered}" /></h4>
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
                    <h4 style="color: #0000FF"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${country.active_cases}" /> </h4>
                    <p>(Active Cases)</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="feature_item">
                    <i class="flaticon-city"></i>
                    <h4 style="color: red"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${country.critical_cases}"/> </h4>
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
<br>
<br>

<div class="col text-center">
    <h2 class="h2-responsive"><strong>Chart of <c:out value='${country.country_name}' /> </strong></h2>
</div>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <canvas id="myChart"></canvas>
        </div>
        <div class="col-md-6">
            <canvas id="labelChart"></canvas>
        </div>
    </div>

</div>
<hr>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

<script>
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Active", "Recovered", "Death"],
            datasets: [{
                label: '',
                data: [
                    <c:out value='${country.active_cases}' />,
                    <c:out value='${country.total_recovered}' />,
                    <c:out value='${country.total_death}' />

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

    var ctxP1 = document.getElementById("labelChart").getContext('2d');
    var myPieChart1 = new Chart(ctxP1, {
        plugins: [ChartDataLabels],
        type: 'pie',
        data: {
            labels: ["Death", "Recovered", "Active Cases"],
            datasets: [{
                data: [
                    <c:out value='${country.total_death}' />,
                    <c:out value='${country.total_recovered}' />,
                    <c:out value='${country.active_cases}' />,
                ],
                backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870"]
            }]
        },
        options: {
            responsive: true,
            legend: {
                position: 'right',
                labels: {
                    padding: 20,
                    boxWidth: 10
                }
            },
            plugins: {
                datalabels: {
                    formatter: (value, ctx) => {
                        let sum = 0;
                        let dataArr = ctx.chart.data.datasets[0].data;
                        dataArr.map(data => {
                            sum += data;
                        });
                        return (value * 100 / sum).toFixed(2) + "%";
                    },
                    color: 'white',
                    labels: {
                        title: {
                            font: {
                                size: '12'
                            }
                        }
                    }
                }
            }
        }
    });

    var ctxL = document.getElementById("lineChart").getContext('2d');
    var myLineChart = new Chart(ctxL, {
        type: 'line',
        data:{
            labels: [
                <c:forEach var="chart" items="${listCountry}" varStatus="loop">
                "${chart.country_name}"${!loop.last ? ',' : ''}
                </c:forEach>
            ],
            datasets: [{
                label: "Total Cases",
                data: [
                    <c:forEach var="chart" items="${listCountry}" varStatus="loop">
                    ${chart.total_cases}${!loop.last ? ',' : ''}
                    </c:forEach>
                ],
                backgroundColor: [
                    'rgba(105, 0, 132, .2)',
                ],
                borderColor: [
                    'rgba(200, 99, 132, .7)',
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