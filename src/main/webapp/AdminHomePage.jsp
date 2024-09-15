<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="http://localhost:8080/FinalProject/" />
    <style>
        html, body {
            height: 100%;
            margin: 0;
            background-color: #f0f2f5;
            font-family: Arial, sans-serif;
        }
        .navbar {
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 10px 10px;
            z-index: 1000;
            position: fixed;
            top: 0;
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
        .content {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 80px; /* Adjust padding to prevent overlap with fixed navbar */
            width: 100%;
        }
        .signup-form {
            width: 360px;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
            text-align: center;
            margin-top: 20px; /* Add margin-top to ensure form is not too close to the navbar */
        }
        h2 {
            color: green;
            text-align: center;
            margin-top: 20px;
        }
        .tableOut {
            width: 90%;
            margin: 20px 0;
            overflow-x: auto;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="#" class="logo"><img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="Logo"></a>
        <div class="nav-links">
            <a href="SignIn.jsp">SignIn</a>
        </div>
    </div>
    <div class="content">
        <div class="signup-form">
            <h2>${success}</h2>
            <form id="contactForm" action="viewDetails" method="post">
                <div class="mb-1 mt-3">
                    <p class="fw-bold">
                        <h4>View Login details:</h4>
                    </p>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submitBtn" name="submit">View</button>
                </div>
            </form>
            <span style="color:red">${msg}</span>
        </div>
        <c:if test="${dto.isEmpty()==false}">
            <div class="tableOut">
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">First Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone number</th>
                            <th scope="col">Login count</th>
                            <th scope="col">Created By</th>
                            <th scope="col">Created Date</th>
                            <th scope="col">Updated By</th>
                            <th scope="col">Updated Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${dto}" var="signup">
                            <tr>
                                <td scope="row">${signup.getId()}</td>
                                <td>${signup.getFirstName()}</td>
                                <td>${signup.getLastName()}</td>
                                <td>${signup.getEmail()}</td>
                                <td>${signup.getPhoneNumber()}</td>
                                <td>${signup.getLogin_count()}</td>
                                <td>${signup.getCreatedBy()}</td>
                                <td>${signup.getCreatedDate()}</td>
                                <td>${signup.getUpdatedBy()}</td>
                                <td>${signup.getUpdatedDate()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</body>
</html>
