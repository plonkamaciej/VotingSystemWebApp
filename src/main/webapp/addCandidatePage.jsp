<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>Add Candidate</title>
</head>
<body class="container mt-4">

<h1>Add Candidate</h1>

<form action="/VotingSystemWebApp-1.0-SNAPSHOT/addCandidate" method="post" class="mt-3">
    <div class="input-group">
        <input type="text" name="candidateName" class="form-control" placeholder="Enter candidate name" required>
        <div class="input-group-append">
            <button type="submit" class="btn btn-primary">Add Candidate</button>
        </div>
    </div>
</form>

<button class="btn btn-secondary mt-3" onclick="navigateToVoteSystem()">Back to Vote System</button>

<script>
    function navigateToVoteSystem() {
        window.location.href = "/VotingSystemWebApp-1.0-SNAPSHOT/";
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
