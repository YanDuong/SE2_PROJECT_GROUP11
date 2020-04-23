<script src="https://kit.fontawesome.com/yourcode.js"></script>

<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="assets/css/style.css">

<style>
    navbar-brand{
        padding-left: 20px;
    }
</style>

<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
    <div class="container">
        <div class="navbar-header">
            <a href="<%=request.getContextPath()%>/ShowAllCountryUserViewServlet" class="navbar-brand">Home</a>
            <a href="<%=request.getContextPath()%>/ShowAllCountryUserViewServlet" class="navbar-brand">WORLD</a>
            <a href="<%=request.getContextPath()%>/ShowAllProvinceUserViewServlet" class="navbar-brand">VIETNAM</a>
            <a href="${pageContext.request.contextPath}/login.jsp" class="navbar-brand">Login</a>
        </div>
    </div>
</header>