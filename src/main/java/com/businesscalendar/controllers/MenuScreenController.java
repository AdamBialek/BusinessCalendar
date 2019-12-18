package com.businesscalendar.controllers;

import com.businesscalendar.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class MenuScreenController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    //*****************************WSZYSTKO DLA KALENDARZA*********************************:

    private Note note;

    @FXML
    private Label calendarMonthLabel;

    @FXML
    private GridPane calendarDaysGridPane;

    public GridPane getCalendarDaysGridPane() {
        return calendarDaysGridPane;
    }

    public void setCalendarDaysGridPane(GridPane calendarDaysGridPane) {
        this.calendarDaysGridPane = calendarDaysGridPane;
    }

    @FXML
    public void prevMonthSwitch(){
        note.previous();
        setCalendarDaysGridPaneContent();
    }

    @FXML
    public void nextMonthSwitch(){
        note.next();
        setCalendarDaysGridPaneContent();
    }

    @FXML
    public void onDateClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChooseScreen.fxml"));
        fxmlLoader = note.onClick(getCalendarDaysGridPane(),fxmlLoader);
        AnchorPane anchorPane = fxmlLoader.load();
        ChooseScreenController chooseScreenController = fxmlLoader.getController();
        chooseScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    public void setCalendarDaysGridPaneContent(){
        GridPane gridPane=note.setDatesToDisplayInWindows(getCalendarDaysGridPane());
        setCalendarDaysGridPane(note.setDatesToWindows(note.getDaysMonth(),note.getThisMonth(),note.getWeekdayAtStartOfMonth(),gridPane));
        calendarMonthLabel.setText(note.getThisMonth().toString()+"\n"+note.getTodayDate().getYear());
    }

    //*****************************WSZYSTKO DLA POGODY*********************************:

    private Weather weather;

    @FXML
    private Label weatherDataInput;

    @FXML
    private TextField cityName;

    @FXML
    public void checkWeatherButton() {
        String weatherInfo=weather.getWeatherInfo(cityName.getText());
        cityName.setText("");
        weatherDataInput.setText(weatherInfo);
    }

    //*****************************WSZYSTKO DLA KALKULATORA*********************************:

    private Calculator calculator;

    @FXML
    private TextField calculatorScreen;

    @FXML
    public void button1() {
        calculatorScreen.setText(calculator.assignButtonValue(1));
    }

    @FXML
    public void button2() {
        calculatorScreen.setText(calculator.assignButtonValue(2));
    }

    @FXML
    public void button3() {
        calculatorScreen.setText(calculator.assignButtonValue(3));
    }

    @FXML
    public void button4() {
        calculatorScreen.setText(calculator.assignButtonValue(4));
    }

    @FXML
    public void button5() {
        calculatorScreen.setText(calculator.assignButtonValue(5));
    }

    @FXML
    public void button6() {
        calculatorScreen.setText(calculator.assignButtonValue(6));
    }

    @FXML
    public void button7() {
        calculatorScreen.setText(calculator.assignButtonValue(7));
    }

    @FXML
    public void button8() {
        calculatorScreen.setText(calculator.assignButtonValue(8));
    }

    @FXML
    public void button9() {
        calculatorScreen.setText(calculator.assignButtonValue(9));
    }

    @FXML
    public void button0() {
        calculatorScreen.setText(calculator.assignButtonValue(0));
    }

    @FXML
    public void buttonPoint() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.numberToFraction(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonEquals() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.equals(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonAdd() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.addition(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonSubtract() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.subtraction(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonMultiply() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.multiplication(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonDivide() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.division(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonBackspace() {
        calculatorScreen.setText(calculator.erase());
    }

    @FXML
    public void buttonClear() {
        calculator.clearScreen();
        calculatorScreen.setText("");
    }

    @FXML
    public void plusMinus() {
        String newCalcScrTxt=calculator.negate();
        calculatorScreen.setText(newCalcScrTxt);
    }

    //*****************************WSZYSTKO DLA NEWSÓW*********************************:

    private Article article1;

    private Article article2;

    private Article article3;

    private int articlesToDisplay;

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

    public void setArticle2(Article article2) {
        this.article2 = article2;
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
    private Label nyt;

    @FXML
    private Hyperlink articleSpace1Link;

    @FXML
    private Hyperlink articleSpace2Link;

    @FXML
    private Hyperlink articleSpace3Link;

    public void setArticles(){
        setArticle1(articleList.get(0));
        setArticle2(articleList.get(1));
        setArticle3(articleList.get(2));
    }

    public void getHeadlines(){
        List<Article> articleLinkedList = new LinkedList<>();
        try {
            articleLinkedList= article1.parseJson(article1.getResponse(article1.buildUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setArticleList(articleLinkedList);
        setArticles();
        articleSpace1Link.setText(article1.getTitle());
        articleSpace2Link.setText(article2.getTitle());
        articleSpace3Link.setText(article3.getTitle());
        setSwitchArticles(0);
        newer.setDisable(true);
        older.setDisable(false);
    }

    public void markLinksAsNotVisited(){
        articleSpace1Link.setVisited(false);
        articleSpace2Link.setVisited(false);
        articleSpace3Link.setVisited(false);
    }

    @FXML
    public void worldSwitch() {
        article1.chooseNews("world");
        nyt.setText("New York Times World News");
        article1.buildUrl();
        markLinksAsNotVisited();
        getHeadlines();
    }

    @FXML
    public void businessSwitch() {
        article1.chooseNews("business");
        nyt.setText("New York Times Business News");
        article1.buildUrl();
        markLinksAsNotVisited();
        getHeadlines();
    }

    @FXML
    public void politicsSwitch() {
        article1.chooseNews("politics");
        nyt.setText("New York Times Politics News");
        article1.buildUrl();
        markLinksAsNotVisited();
        getHeadlines();
    }

    @FXML
    public void techSwitch() {
        article1.chooseNews("technology");
        nyt.setText("New York Times Technology News");
        article1.buildUrl();
        markLinksAsNotVisited();
        getHeadlines();
    }

    @FXML
    public void sportsSwitch() {
        article1.chooseNews("sports");
        nyt.setText("New York Times Sports News");
        article1.buildUrl();
        markLinksAsNotVisited();
        getHeadlines();
    }

    @FXML
    public void travelSwitch() {
        article1.chooseNews("travel");
        nyt.setText("New York Times Travel News");
        article1.buildUrl();
        markLinksAsNotVisited();
        getHeadlines();
    }

    @FXML
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
        if(now>=limit+3){
            setArticle1(articleList.get(now-3));
            setArticle2(articleList.get(now-2));
            setArticle3(articleList.get(now-1));
            articleSpace1Link.setText(article1.getTitle());
            articleSpace2Link.setText(article2.getTitle());
            articleSpace3Link.setText(article3.getTitle());
            markLinksAsNotVisited();
            setSwitchArticles(now-3);
            older.setDisable(false);
        } else {
            newer.setDisable(true);
        }
        if(article1.getTitle().equals(articleList.get(0).getTitle())){
            newer.setDisable(true);
        }
    }

    @FXML
    public void oldNews() {
        int now = getSwitchArticles();
        int limit = getArticleList().size();
        if(now<limit-6){
            setArticle1(articleList.get(now+3));
            setArticle2(articleList.get(now+4));
            setArticle3(articleList.get(now+5));
            articleSpace1Link.setText(article1.getTitle());
            articleSpace2Link.setText(article2.getTitle());
            articleSpace3Link.setText(article3.getTitle());
            markLinksAsNotVisited();
            setSwitchArticles(now+3);
            newer.setDisable(false);
        } else {
            older.setDisable(true);
            setArticle1(articleList.get(articleList.size()-3));
            setArticle2(articleList.get(articleList.size()-2));
            setArticle3(articleList.get(articleList.size()-1));
            articleSpace1Link.setText(article1.getTitle());
            articleSpace2Link.setText(article2.getTitle());
            articleSpace3Link.setText(article3.getTitle());
            markLinksAsNotVisited();
            setSwitchArticles(now+3);
            newer.setDisable(false);
        }
    }

//    ***********FUNKCJONALNOSCI KONTA USERA***********:

    private Connection connection;

    private Login login;

    private CRUD crud;

    @FXML
    private void logOut() {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void deleteAccount() throws SQLException {
        crud.deleteUserNotes();
        crud.deleteUser();
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void changeEmail() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChangeEmail.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ChangeEmailController changeEmailController = fxmlLoader.getController();
        changeEmailController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    private void contactUs() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/SendMail.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        SendMailController sendMailController = fxmlLoader.getController();
        sendMailController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    void initialize() {
//        *********KALENDARZ************:
        note=new Note();
        note.setTodayDate(LocalDate.now());
        note.setThisMonth(note.getTodayDate().getMonth());
        System.out.println(note.getWeekdayAtStartOfMonth());
        setCalendarDaysGridPaneContent();
//        *********POGODA*********:
        weather=new Weather();
//        *********KALKULATOR***********:
        calculator=new Calculator();
//        ********NEWS API***********:
        article1 = new Article();
        getHeadlines();
//        ********USUN USERA*********:
        connection=new SQLConnection().getConnection();
        crud=new CRUD(connection);
        login=new Login();
    }
}