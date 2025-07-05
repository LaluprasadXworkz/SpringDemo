<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/common-module/">
                <img src="https://www.x-workz.in/Logo.png" alt="xyz" height="50" class="d-inline-block align-text-top" />
            </a>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/common-module/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/common-module/home/register">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/common-module/login">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2 class="mb-4 text-center text-success">Profile Update </h2>
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <form method="post" action="/common-module/update" enctype="multipart/form-data">
                    <input type="text"  name="registerId" value="${register.registerId}"  />

                    <div class="mb-3">
                        <label for="userName" class="form-label">Username</label>
                        <input type="text" class="form-control" id="userName" name="userName" value="${register.userName}" />
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone Number</label>
                        <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${register.phoneNumber}"/>
                        <span class="error" id="phoneNumberError"></span>
                    </div>

                     <div class="mb-3">
                        <label for="photo" class="form-label">Uplode image </label>
                        <input type="file" class="form-control" id="photo" name="file"/>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary px-4">Edit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>