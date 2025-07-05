<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>OTP Verification</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
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
                        <a class="nav-link active" href="/common-module/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/common-module/home/register">Register</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header text-center">
                        <h4>OTP Verification</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="/common-module/validateOtp" method="post">
                            <input type="hidden" id="email" name="email" value="${email}" />

                            <div class="mb-3">
                                <label for="otp" class="form-label">Enter OTP sent to ${email}</label>
                                <input type="text" class="form-control" id="otp" name="otp"
                                       pattern="[0-9]{6}" title="6 digit OTP" required />
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">Verify OTP</button>
                                <button type="button" class="btn btn-secondary"
                                        onclick="window.location.href='/common-module/admin'">Back</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>