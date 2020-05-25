<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Covid Tracker</title>
    <style>
        #login{
            font-family: 'Poppins', sans-serif;
            font-size: 45px;
        }
        #main{
            min-height: calc(100vh - 96px - 255px);
        }

    </style>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,600;1,400&display=swap" rel="stylesheet">

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


<jsp:include page="navbar.jsp"></jsp:include>


<!-- Material form login -->
<div class="container mb-md-5" id="main">
    <div class="col-md-6 mx-auto text-center">
        <div class="header-title">
            <h1 class="wv-heading--title" id="login">
                Login Form
            </h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 mx-auto">
            <div class="myform form ">
                <form action="login" method="post" name="login">
                    <div class="form-group">
                        <input type="text" name="email"  class="form-control my-input" id="email" placeholder="email" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password"  class="form-control my-input" id="pwd" placeholder="password" required="required">
                    </div>
                    <div class="text-center " style="color: #00cc00">
                        <p style="color: red">${message}</p>
                        <p>${errors.passwordError}</p>
                        <br>
                        <br>
                        <button type="submit" class=" btn btn-block send-button tx-tfm">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>
