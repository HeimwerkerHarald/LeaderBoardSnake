<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Snake</title>
    <style>
        body{
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;}

        canvas{
            box-shadow: black 20px 10px 50px;

        }
    </style>
</head>
<body>
<h1>Snake</h1>
<button onclick="location.reload()" id="restartGame" class="restart">Restart Game</button>
<br/>
<a href="highScore">View Highscore</a>
<br/>
<p id="userName"></p>
<br/>
<canvas id="game" width="400" height="400"></canvas>
<script src="SnakeMain.js"></script>
</body>
</html>
