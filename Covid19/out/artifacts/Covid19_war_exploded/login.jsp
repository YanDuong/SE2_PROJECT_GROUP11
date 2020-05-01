<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Covid Tracker</title>

    <script src="https://kit.fontawesome.com/yourcode.js"></script>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">


    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet"  type="text/css" href="assets/css/style.css">
</head>
<body>
<script type="text/javascript">
    $(document).ready(function() {
        $("#loginForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },

                password: "required",
            },

            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter a valid email address"
                },

                password: "Please enter password"
            }
        });

    });
</script>


<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
    <div class="container">
        <div class="navbar-header">
            <a href="<%=request.getContextPath()%>/ShowAllCountryUserViewServlet" class="navbar-brand">Home</a>
        </div>
    </div>
</header>
<!-- Material form login -->
<div class="container">
    <div class="col-md-6 mx-auto text-center">
        <div class="header-title">
            <h1 class="wv-heading--title">
                Login Form
            </h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 mx-auto">
            <div class="myform form ">
                <form action="login" method="post" name="login">
                    <div class="form-group">
                        <input type="text" name="email"  class="form-control my-input" id="email" placeholder="email">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password"  class="form-control my-input" id="pwd" placeholder="password">
                    </div>
                    <div class="text-center " style="color: #00cc00">
                        <p style="color: red">${message}</p>
                        <p>${errors.passwordError}</p>
                        <button type="submit" class=" btn btn-block send-button tx-tfm">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


</body>

</html>
