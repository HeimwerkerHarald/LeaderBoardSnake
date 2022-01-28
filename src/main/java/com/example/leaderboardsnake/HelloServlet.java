package com.example.leaderboardsnake;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "highScore", value = "/highScore")
public class HelloServlet extends HttpServlet {

    public class usernameAndScore{
        private String username;
        private int score;
    }

    public ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        usernameAndScore usernameAndScore = new usernameAndScore();
        objectMapper.writeValue(new File("target/score.json"),usernameAndScore);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        objectMapper.readValue(new File("target/score.json"),usernameAndScore.class);
    }

    public void destroy() {
    }
}