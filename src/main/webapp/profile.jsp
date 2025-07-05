<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        .profile-img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 10px;
        }
        .profile-img-large {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            margin: 0 auto 20px;
            display: block;
            border: 3px solid #ddd;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/common-module/">
                <img src="https://www.x-workz.in/Logo.png" alt="xyz" height="50" class="d-inline-block align-text-top" />
            </a>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <div class="d-flex align-items-center">
                            <img src="display?imagepath=${register.getImageName()}" alt="Profile" class="profile-img">
                    <span class="text-white me-3">${register.userName}</span>
                </div>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="updatePage?id=${register.registerId}">Update</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/common-module/login">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center">

                        <img src="display?imagepath=${register.getImageName()}" alt="Profile" class="profile-img-large">

                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <td>${register.userName}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>${register.email}</td>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td>${register.phoneNumber}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>