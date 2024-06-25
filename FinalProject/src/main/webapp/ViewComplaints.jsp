<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View Complaints</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
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
    .container {
        margin-top: 100px;
    }
    .card {
        max-width: 500px;
        margin: 0 auto;
    }
    body {
        background-color: white;
    }
</style>
<body>
    <div class="navbar">
        <a href="#" class="logo"><img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="Logo"></a>
        <div class="nav-links">
            <a href="ViewComplaints.jsp">View Complaints</a>
            <a href="UserHomePage.jsp">User Home Page</a>
        </div>
    </div>

    <div class="container mt-5">
        <div class="d-flex justify-content-center mb-4">
            <div class="card px-5 py-4 bg-light">
                <div class="card-body">
                    <span class="text-success fs-4 fw-bold">${complaintMsg}</span>
                    <h6 class="card-title mb-3 text-dark fs-1">View Complaints</h6>

                    <form action="viewComplaints" method="post">
                        <div class="mb-4">
                            <label for="status" class="form-label text-dark">Complaints Status</label>
                            <select class="form-select" id="status" name="status" onblur="setGroup()">
                                <option value="0" ${selectedStatus == null ? 'selected' : ''}>Choose...</option>
                                <option value="active" ${selectedStatus == 'active' ? 'selected' : ''}>Active</option>
                                <option value="resolved" ${selectedStatus == 'resolved' ? 'selected' : ''}>Resolved</option>
                            </select>
                            <span id="groupError" class="text-danger"></span>
                        </div>
                        <div class="mb-1 mt-3">
                            <input type="submit" value="Search" name="submit" id="submit" class="btn btn-primary me-5">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${not empty complaints}">
        <div class="container mt-5">
            <table class="table table-striped table-light">
                <thead class="text-dark fw-bold">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Type</th>
                        <th scope="col">Area</th>
                        <th scope="col">Description</th>
                        <th scope="col">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${complaints}" var="complaint">
                        <tr class="text-dark fw-bold">
                            <th scope="row">${complaint.id}</th>
                            <td>${complaint.complaintType}</td>
                            <td>${complaint.area}</td>
                            <td>${complaint.description}</td>
                            <td>${complaint.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <c:if test="${empty complaints}">
        <div class="container">
            <p class="text-center text-muted">No complaints found.</p>
        </div>
    </c:if>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>
