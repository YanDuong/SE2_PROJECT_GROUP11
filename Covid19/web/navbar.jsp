
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <meta charset="UTF-8">
    <jsp:include page="initial-load.jsp"></jsp:include>

</head>
<body>
    <!--Navbar -->
    <nav class="mb-1 navbar navbar-expand-lg navbar-dark default-color">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/ShowAllCountryServlet">Covid-19</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-333"
                aria-controls="navbarSupportedContent-333" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent-333">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                </li>
                <li class="nav-item">
                    <button onclick="window.location.href='${pageContext.request.contextPath}/ShowAllCountryServlet'" class="btn btn-sm align-middle btn-outline-white" type="button">World</button>
                </li>
                <li class="nav-item">
                    <button onclick="window.location.href='${pageContext.request.contextPath}/ShowAllContinentServlet'" class="btn btn-sm align-middle btn-outline-white" type="button">Continent</button>
                </li>
                <li class="nav-item">
                    <button onclick="window.location.href='${pageContext.request.contextPath}/ShowAllProvinceServlet'" class="btn btn-sm align-middle btn-outline-white" type="button">Vietnam</button>
                </li>
                <li class="nav-item">
                    <button onclick="window.location.href='${pageContext.request.contextPath}/ShowAllNewsServlet'" class="btn btn-sm align-middle btn-outline-white" type="button">News</button>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto nav-flex-icons">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">

                        <%if (session.getAttribute("admin") != null){%>
                        <i class="fas fa-user"> Admin</i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right dropdown-default"
                             aria-labelledby="navbarDropdownMenuLink-333">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">logout</a>
                        </div>
                        <%}else{%>
                        <i class="fas fa-user"> User</i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right dropdown-default"
                             aria-labelledby="navbarDropdownMenuLink-333">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                        </div>
                        <%}%>
                </li>
            </ul>
        </div>
    </nav>
    <!--/.Navbar -->

</body>

</html>
