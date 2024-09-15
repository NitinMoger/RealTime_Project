<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="http://localhost:8080/FinalProject/" />
    <style>
        body {
            background-color: #f0f2f5;
            margin: 0;
        }
        .signup-form {
            width: 360px;
            margin: 100px auto;
            font-family: Arial, sans-serif;
        }
        .signup-form form {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
        }
        .logo-img {
            margin-right: 15px;
        }
        .form-container {
            padding-top: 80px;
        }
        .navbar {
            position: fixed; /* Fixed position so it stays at the top */
            top: 0; /* Stick to the top */
            width: 100%; /* Take full width of the viewport */
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: rgba(0, 0, 0, 0.7); /* Semi-transparent background */
            padding: 10px 10px; /* Corrected padding */
            z-index: 1000; /* Ensure it's above other content */
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
            padding: 10px 10px; /* Corrected padding */
            margin-left: 20px; /* Space between links */
        }
        .navbar .nav-links a:hover {
            color: #35e4cc; /* Change color on hover for better visibility */
        }
        .heading {
            text-align: center;
            color: white;
            margin-top: 100px; /* Adjust top margin for heading */
            font-size: 48px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="#" class="logo"><img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="Logo"></a>
        <div class="nav-links">
            <a href="Signup.jsp">Signup</a>
            <a href="SignIn.jsp">SignIn</a>
            <a href="PasswordReset.jsp">PasswordReset</a>
            <a href="ResetPasswordAnyTime.jsp">ResetPasswordAnyTime</a>
            <a href="AdminSignIn.jsp">AdminSignIn</a>
            <a href="UserEditProfile.jsp">UserEditProfile</a>
              <a href="RaiseComplaint.jsp">RaiseComplaint</a>


        </div>
    </div>
    <div class="signup-form form-container">
        <form id="contactForm" action="loan" method="post">
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>Tech Stack:</h4>
                    <span>JSP, Bootstrap CSS, JavaScript, Java, Spring, Spring JPA/Hibernate.</span>
                </p>
            </div>
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>Start Date:</h4>
                    <span>6-11-2024</span>
                </p>
            </div>
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>VCS:</h4>
                    <a href="https://github.com/NitinMoger">GitHub</a>
                </p>
            </div>
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>Description:</h4>
                    <span>Currently working on designing Landing Page and Sign Up page. Saving data to the database.</span>
                </p>
            </div>
        </form>
    </div>
</body>
</html>
