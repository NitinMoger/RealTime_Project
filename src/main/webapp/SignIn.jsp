<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f0f2f5;
        }
        .login-form {
            width: 360px;
            margin: 100px auto;
            font-family: Arial, sans-serif;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
        .login-link {
            text-align: center;
            margin-top: 20px;
        }
        .error {
            color: red;
            font-size: 12px;
        }
        .navbar {
            position: fixed;
            top: 0;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 10px 10px;
            z-index: 1000;
        }
        .navbar .logo img {
            height: 60px;
        }
        .navbar .nav-links {
            display: flex;
            align-items: center;
        }
        .navbar .nav-links a {
            color: white;
            font-size: 18px;
            text-decoration: none;
            padding: 10px 10px;
            margin-left: 20px;
        }
        .navbar .nav-links a:hover {
            color: #35e4cc;
        }
        .heading {
            text-align: center;
            color: white;
            margin-top: 100px;
            font-size: 48px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="#" class="logo"><img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="Logo"></a>
        <div class="nav-links">
            <a href="index.jsp">Home</a>
            <a href="PasswordReset.jsp">Password Reset</a>
        </div>
    </div>

    <div class="login-form">
        <form action="signIn" method="post">
            <h2 class="text-center">Login</h2>
            <span style="color:red">${msg}</span>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Enter email" id="email" name="email" required>
                <div id="error-email" class="error"></div>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Enter Password" id="password" name="password" required>
                <div id="error-password" class="error"></div>
            </div>
            <div class="mb-3">
                <div class="captcha">
                    <label for="captcha-input">Enter Captcha</label>
                    <div id="captchaPreview" class="mb-2 bg-white p-2 text-center border"></div>
                    <div class="captcha d-flex align-items-center">
                        <input type="text" name="captcha" id="captcha" placeholder="Enter captcha text" class="form-control me-2">
                        <button type="button" class="btn btn-secondary" onclick="generateCaptcha()">&#8635;</button>
                    </div>
                    <span id="captchaError" class="text-danger"></span>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block" id="submitBtn" name="submit">Login</button>
            </div>
            <div class="clearfix">
                <a href="FindByEmail.jsp" class="float-right">Forgot Password?</a>
            </div>
        </form>
        <p class="login-link">Don't have an account?
            <a href="Signup.jsp">Sign up here</a>
        </p>
    </div>

    <script>
        const emailInput = document.getElementById('email');
        const passwordInput = document.getElementById('password');
        const submitBtn = document.getElementById('submitBtn');
        let captchaCode = '';

        emailInput.addEventListener('input', function() {
            const inputValue = this.value.trim();
            const errorEmail = document.getElementById('error-email');

            if (!inputValue.includes('@') || !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(inputValue) || inputValue.length <= 8 || inputValue.length >= 38) {
                errorEmail.textContent = 'Email must be valid and contain @, special characters, digits, and be between 8 and 28 characters.';
            } else {
                errorEmail.textContent = '';
            }
            validateForm();
        });

        passwordInput.addEventListener('input', function() {
            const inputValue = this.value.trim();
            const errorPassword = document.getElementById('error-password');

            const hasAlphabet = /[a-zA-Z]/.test(inputValue);
            const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(inputValue);
            const isValidLength = inputValue.length > 6 && inputValue.length < 18;

            if (!isValidLength) {
                errorPassword.textContent = 'Password length should be greater than 6 and less than 18.';
            } else {
                errorPassword.textContent = '';
            }
            validateForm();
        });

        function validateForm() {
            const errorEmail = document.getElementById('error-email').textContent;
            const errorPassword = document.getElementById('error-password').textContent;
            if (errorEmail === '' && errorPassword === '' && getFields["captcha"]) {
                submitBtn.removeAttribute('disabled');
            } else {
                submitBtn.setAttribute('disabled', 'disabled');
            }
        }

        let getFields = {
            "captcha": false
        };

        function validate() {
            let flag = false;

            for (let [key, value] of Object.entries(getFields)) {
                if (value === false) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                submitBtn.removeAttribute("disabled");
            } else {
                submitBtn.setAttribute("disabled", "disabled");
            }
        }

        function generateCaptcha() {
            let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
            captchaCode = '';
            for (let i = 0; i < 6; i++) {
                captchaCode += characters.charAt(Math.floor(Math.random() * characters.length));
            }
            document.getElementById("captchaPreview").innerText = captchaCode;
            getFields["captcha"] = false; // Reset captcha validation
            document.getElementById("captchaError").innerText = ""; // Clear any previous error message
            validate();
        }

        function setCaptcha() {
            let captchaInput = document.getElementById("captcha").value.trim();
            let error = document.getElementById("captchaError");

            if (captchaInput === captchaCode) {
                getFields["captcha"] = true;
                error.innerHTML = "";
            } else {
                error.innerHTML = "Please enter the correct captcha.";
                error.style.color = 'red';
                getFields["captcha"] = false;
            }
            validate();
        }

        document.addEventListener("DOMContentLoaded", function() {
            generateCaptcha();

            document.getElementById("captcha").addEventListener("input", setCaptcha);

            document.querySelector("form").addEventListener("submit", function(event) {
                setCaptcha(); // Validate captcha on form submit

                if (!getFields["captcha"]) {
                    event.preventDefault(); // Prevent form submission if captcha is incorrect
                }
            });
        });
    </script>
</body>
</html>
