<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8>
<link href="css/login.css" rel='stylesheet' type = "text/css">
        <title>Login page</title>

    </head>
    <body>
        <form method = "POST" action = "LoginController">
            Administration Name: <input type = "text" name = "adminName" value = ""><br/>
           
            Password: <input type = "text" name = "password" value = ""><br/>
        <div class = "save_password">
            <label class = "checkbox">
                <input type = "checkbox" name = "checkbox" checked><i></i>Remember password
            </label>
        </div>
        <div class = "submit_form">
            <input type = "submit" name = "login_in">
        </div>
        <div class = "error_message">
        
        </div>
        </form>
    </body>
</html>