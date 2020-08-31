<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <form method="post" enctype="multipart/form-data" action="/files">
        <div class="form-group">
            <label for="fileUpload">File to upload:</label>
             <input required="true" type="file" class="form-control-file" id="fileUpload" name="file">
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" id="defaultCheck1" name="isNeedImage" value="true"/>
            <label class="form-check-label" for="defaultCheck1">
                Generate image
            </label>
        </div>
        <input class="btn btn-primary" type="submit" value="Upload"> Press here to upload the file!
    </form>
    <br/>

    <br/>

    <div class="list-group">
        <c:forEach var="listValue" items="${files}">
            <a href="<c:url value='/download/${listValue}'/>" class="list-group-item list-group-item-action">${listValue}</a>
        </c:forEach>
    </div>
    <br>

    <div class="list-group" style="width: 50%; height: 50%">
        <c:forEach var="graph" items="${graph}">
            <img src="data:image/png;base64,${graph}"/>
        </c:forEach>
    </div>
</div>
</body>
</html>
