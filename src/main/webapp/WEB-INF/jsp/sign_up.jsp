<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <form method="POST" action="/auth/sign_up">
        <div class="form-group">
            <label for="username">User name</label>
            <input name="username" type="text" class="form-control" id="username" placeholder="userName">
        </div>

        <div class="form-group">
            <label for="email">User name</label>
            <input name="email" type="text" class="form-control" id="email" placeholder="Email">
        </div>

        <div class="form-group">
            <label for="firstName">User name</label>
            <input name="firstName" type="text" class="form-control" id="firstName" placeholder="First Name">
        </div>

        <div class="form-group">
            <label for="lastName">User name</label>
            <input name="lastName" type="text" class="form-control" id="lastName" placeholder="Last Name">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input name="password" type="password" class="form-control" id="password" placeholder="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
</body>
</html>