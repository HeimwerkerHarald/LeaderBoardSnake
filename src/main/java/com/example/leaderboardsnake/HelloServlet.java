package com.example.leaderboardsnake;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "highScore", value = "/highScore")
public class HelloServlet extends HttpServlet {
    public Highscore newHighscoreReached(Highscore highscore, String name, int score){
        if (score >= highscore.getFirst()) {
            highscore.setFifth(highscore.getFourth());
            highscore.setName5(highscore.getName4());
            highscore.setFourth(highscore.getThird());
            highscore.setName4(highscore.getName3());
            highscore.setThird(highscore.getSecond());
            highscore.setName3(highscore.getName2());
            highscore.setSecond(highscore.getFirst());
            highscore.setName2(highscore.getName1());
            highscore.setFirst(score);
            highscore.setName1(name);
            highscore.setRank("ersten");
        } else if (score >= highscore.getSecond() && score < highscore.getFirst()) {
            highscore.setFifth(highscore.getFourth());
            highscore.setName5(highscore.getName4());
            highscore.setFourth(highscore.getThird());
            highscore.setName4(highscore.getName3());
            highscore.setThird(highscore.getSecond());
            highscore.setName3(highscore.getName2());
            highscore.setSecond(score);
            highscore.setName2(name);
            highscore.setRank("zweiten");
        } else if (score >= highscore.getThird() && score < highscore.getSecond()) {
            highscore.setFifth(highscore.getFourth());
            highscore.setName5(highscore.getName4());
            highscore.setFourth(highscore.getThird());
            highscore.setName4(highscore.getName3());
            highscore.setThird(score);
            highscore.setName3(name);
            highscore.setRank("dritten");
        } else if (score >= highscore.getFourth() && score < highscore.getThird()) {
            highscore.setFifth(highscore.getFourth());
            highscore.setName5(highscore.getName4());
            highscore.setFourth(score);
            highscore.setName4(name);
            highscore.setRank("vierten");
        } else if (score >= highscore.getFifth() && score < highscore.getFourth()) {
            highscore.setFifth(score);
            highscore.setName5(name);
            highscore.setRank("fÃ¼nften");
        }else{
            highscore.setRank("keinen");
        }
        return highscore;
    }
    public static class Highscore {

        int first;
        int second;
        int third;
        int fourth;
        int fifth;
        String name1;
        String name2;
        String name3;
        String name4;
        String name5;
        String rank;

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public int getThird() {
            return third;
        }

        public void setThird(int third) {
            this.third = third;
        }

        public int getFourth() {
            return fourth;
        }

        public void setFourth(int fourth) {
            this.fourth = fourth;
        }

        public int getFifth() {
            return fifth;
        }

        public void setFifth(int fifth) {
            this.fifth = fifth;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public String getName3() {
            return name3;
        }

        public void setName3(String name3) {
            this.name3 = name3;
        }

        public String getName4() {
            return name4;
        }

        public void setName4(String name4) {
            this.name4 = name4;
        }

        public String getName5() {
            return name5;
        }

        public void setName5(String name5) {
            this.name5 = name5;
        }
    }

    public ObjectMapper objectMapper = new ObjectMapper();
    private Highscore highscore;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        int highscoreNumber = Integer.parseInt(req.getParameter("highscore"));

        if(username.length() == 0){
            username = "unnamed";
        }

        ObjectMapper highscoreMapper = new ObjectMapper();
        highscore = highscoreMapper.readValue(new File("C:\\Users\\hvanrooyen\\IdeaProjects\\demo\\demo\\LeaderBoardSnake\\src\\main\\webapp\\package.json"), Highscore.class);

        highscore = newHighscoreReached(highscore, username, highscoreNumber);


        highscoreMapper.writeValue(new File("C:\\Users\\hvanrooyen\\IdeaProjects\\demo\\demo\\LeaderBoardSnake\\src\\main\\webapp\\package.json"), highscore);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.setContentType("text/html");

        ObjectMapper highscoreMapper = new ObjectMapper();
        Highscore highscore = highscoreMapper.readValue(new File("C:\\Users\\hvanrooyen\\IdeaProjects\\demo\\demo\\LeaderBoardSnake\\src\\main\\webapp\\package.json"), Highscore.class);

        PrintWriter out = resp.getWriter();
        out.println(
                "<hr />\n" +
                "<h1><img style=\"font-size: 14px; display: block; margin-left: auto; margin-right: auto;\" src=\"https://www.novomind.com/typo3conf/ext/extension-kwi/Resources/Public/Frontend/Img/novomind/og_novomind_Logo_1800_1080.png\" alt=\"Intelligente Softwarel&ouml;sungen f&uuml;r Commerce und Customer Service - novomind  AG\" width=\"244\" height=\"147\" /></h1>\n" +
                "<h2 style=\"text-align: center;\">Snake Leaderboard</h2>\n" +
                "<p>&nbsp;</p>" + "<br>" +
                "<h3 align=center id=h3>" + highscore.getName1() + "&nbsp" + highscore.getFirst() + "</h3>" +
                "<hr />\n");
    }
}