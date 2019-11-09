package com.businesscalendar.controllers;

import com.businesscalendar.Article;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class MainScreenController {


    private Article article1;

    private Article article2;

    private Article article3;


    private int articlesToDisplay;

    public Article getArticle1() {
        return article1;
    }

    public String madeChoice;

    public void setMadeChoice(String madeChoice) {
        this.madeChoice = madeChoice;
    }

    @FXML
    public List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void setArticle1(Article article1) {
        this.article1 = article1;
    }

    public Article getArticle2() {
        return article2;
    }

    public void setArticle2(Article article2) {
        this.article2 = article2;
    }

    public Article getArticle3() {
        return article3;
    }

    public void setArticle3(Article article3) {
        this.article3 = article3;
    }

    public int getSwitchArticles() {
        return articlesToDisplay;
    }

    public void setSwitchArticles(int switchArticles) {
        this.articlesToDisplay = switchArticles;
    }



    @FXML
    private Button newer;

    @FXML
    private Button older;

    @FXML
    private Hyperlink articleSpace1Link;

    @FXML
    private Hyperlink articleSpace2Link;

    @FXML
    private Hyperlink articleSpace3Link;

    @FXML
    private ImageView articleSpace1Img;

    public void modifyArticlesToDisplay(int number){
        setSwitchArticles(number);
    }

    public void setArticles(){
        if(articlesToDisplay==0){
        setArticle1(articleList.get(0));
        setArticle2(articleList.get(1));
        setArticle3(articleList.get(2));
        }
    }

    public void openUrl(String url) throws IOException {
        if(System.getProperty("os.name").toLowerCase().substring(0,3).equals("win")){
            Runtime rt = Runtime.getRuntime();
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } else if(System.getProperty("os.name").toLowerCase().equals("mac")){
            Runtime rt = Runtime.getRuntime();
            rt.exec("open " + url);
        }
    }



    @FXML
    public void openLink1() throws IOException {
        openUrl(article1.getUrl());
    }

    @FXML
    public void openLink2() throws IOException {
        openUrl(article2.getUrl());
    }

    @FXML
    public void openLink3() throws IOException {
        openUrl(article3.getUrl());
    }

    @FXML
    public void newNews() {
        int now = getSwitchArticles();
        int limit = 0;
        System.out.println(now);
        if(now>=limit+3){
            setArticle1(articleList.get(now-3));
            setArticle2(articleList.get(now-2));
            setArticle3(articleList.get(now-1));
            articleSpace1Link.setText(article1.getTitle());
            articleSpace2Link.setText(article2.getTitle());
            articleSpace3Link.setText(article3.getTitle());
            setSwitchArticles(now-3);
            older.setDisable(false);
        } else {
            newer.setDisable(true);
        }

    }

    @FXML
    public void oldNews() {
        int now = getSwitchArticles();
        int limit = getArticleList().size();
        System.out.println(now);
        if(now<limit-5){
            setArticle1(articleList.get(now+3));
            setArticle2(articleList.get(now+4));
            setArticle3(articleList.get(now+5));
            articleSpace1Link.setText(article1.getTitle());
            articleSpace2Link.setText(article2.getTitle());
            articleSpace3Link.setText(article3.getTitle());
            setSwitchArticles(now+3);
            newer.setDisable(false);
        } else {
            older.setDisable(true);
        }




    }


    @FXML
    void initialize() throws IOException {
        Article article=new Article();
        List<Article> articleLinkedList = new LinkedList<>();
        try {
            articleLinkedList= article.parseJson(article.getResponse(article.buildUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setArticleList(articleLinkedList);
        setArticles();
        setArticle1(articleLinkedList.get(0));
        articleSpace1Link.setText(article1.getTitle());
        articleSpace2Link.setText(article2.getTitle());
        articleSpace3Link.setText(article3.getTitle());


        System.out.println(articleList.size());












    }
}
