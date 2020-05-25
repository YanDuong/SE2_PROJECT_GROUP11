<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="listNews" scope="request" type="java.util.List"/>

<html>
<head>
    <title>News</title>
    <jsp:include page="navbar.jsp"></jsp:include>
<style>
    img {width:100%;}
    #main{
        min-height: calc(100vh - 96px - 255px);
    }
</style>
</head>
<body>
 <div class="container">
    <div class="row">
        <div class="col-md-6 mt-25">
            <h1>COVID-19 of News</h1>
            <p>Latest Covid-19 update.</p>
        </div>
        <%if (session.getAttribute("admin") != null){%>
        <div class="container text-left mt-lg-4">
            <a href="${pageContext.request.contextPath}/AutoUpdateNewsServlet" class="btn btn-success" >Auto update new</a>
        </div>
        <%}%>
    </div>
 </div>
<div class="container" id="main">
    <c:forEach var="news" items="${listNews}">
    <div class="row">
        <div class="col-md-9">
            <div class="row mb-2">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="<c:out value='${news.imageUrl}' />" alt="Img">
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <div class="news-content mb-1">
                                            <a  href="https:\/\/baomoi.com<c:out value='${news.url}' />" ><h3 class="text-info"><c:out value='${news.title}' /></h3></a>
                                        </div>
                                        <div class="news-footer">
                                            <div class="news-author">
                                                <ul class="list-inline list-unstyled">
                                                    <li class="list-inline-item text-secondary">
                                                        <i class="fas fa-user-alt"></i>
                                                        <c:out value='${news.sourceMeta}' />
                                                    </li>
                                                    <li class="list-inline-item text-secondary">
                                                        <i class="fas fa-clock"></i>
                                                        <c:out value='${news.datePublic}'/>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
</div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
