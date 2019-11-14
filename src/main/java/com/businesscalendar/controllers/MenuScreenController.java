package com.businesscalendar.controllers;

import com.businesscalendar.Article;
import com.businesscalendar.NewsType;
import com.businesscalendar.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;


public class MenuScreenController {

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

    //*****************************WSZYSTKO DLA KALKULATORA*********************************:

    private String calculatorNumber1="";

    private String operation="";

    private BigDecimal number1;

    public String getCalculatorNumber1() {
        return calculatorNumber1;
    }

    public void setCalculatorNumber1(String calculatorNumber1) {
        this.calculatorNumber1 = calculatorNumber1;
    }

    @FXML
    private TextField calculatorScreen;

    @FXML
    public void button1() {
        setCalculatorNumber1(getCalculatorNumber1()+"1");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button2() {
        setCalculatorNumber1(getCalculatorNumber1()+"2");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button3() {
        setCalculatorNumber1(getCalculatorNumber1()+"3");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button4() {
        setCalculatorNumber1(getCalculatorNumber1()+"4");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button5() {
        setCalculatorNumber1(getCalculatorNumber1()+"5");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button6() {
        setCalculatorNumber1(getCalculatorNumber1()+"6");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button7() {
        setCalculatorNumber1(getCalculatorNumber1()+"7");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button8() {
        setCalculatorNumber1(getCalculatorNumber1()+"8");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button9() {
        setCalculatorNumber1(getCalculatorNumber1()+"9");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void button0() {
        setCalculatorNumber1(getCalculatorNumber1()+"0");
        calculatorScreen.setText(calculatorNumber1);
    }

    @FXML
    public void buttonPoint() {
        if (!getCalculatorNumber1().contains(".")){
            setCalculatorNumber1(getCalculatorNumber1()+".");
            calculatorScreen.setText(calculatorNumber1);
        }
    }

    @FXML
    public void buttonEquals() {
        if(!getCalculatorNumber1().equals("")){
            calcResult();
            operation="";
        } else {

            setCalculatorNumber1(number1.toString());
            calculatorScreen.setText(getCalculatorNumber1());
            number1=BigDecimal.ZERO;
            operation="";
        }

    }

    @FXML
    public void buttonAdd() {
        if(operation.length()<1){
            operation="+";
            number1=new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            calculatorScreen.setText("");
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            calcResult();
            operation="+";
            setCalculatorNumber1("");
        }
    }

    @FXML
    public void buttonSubtract() {
        if(operation.length()<1) {
            operation = "-";
            number1 = new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            calculatorScreen.setText("");
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            calcResult();
            operation="-";
            setCalculatorNumber1("");
        }
    }

    @FXML
    public void buttonMultiply() {
        if(operation.length()<1) {
            operation = "*";
            number1 = new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            calculatorScreen.setText("");
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            calcResult();
            operation="*";
            setCalculatorNumber1("");
        }
    }

    @FXML
    public void buttonDivide() {
        if(operation.length()<1) {
            operation = "/";
            number1 = new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            calculatorScreen.setText("");
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            calcResult();
            operation="/";
            setCalculatorNumber1("");
        }
    }

    @FXML
    public void buttonPercentage() {}

    @FXML
    public void buttonBackspace() {
        setCalculatorNumber1(getCalculatorNumber1().substring(0,calculatorNumber1.length()-1));
        calculatorScreen.setText(getCalculatorNumber1());
    }

    @FXML
    public void buttonClear() {
        number1=BigDecimal.ZERO;
        calculatorNumber1="";
        operation="";
        calculatorScreen.setText("");
    }

    @FXML
    public void plusMinus() {
        if (getCalculatorNumber1().lastIndexOf("-")==0){
            setCalculatorNumber1(getCalculatorNumber1().replace("-",""));
            calculatorScreen.setText(calculatorNumber1);
        } else if(getCalculatorNumber1().lastIndexOf("-")==-1){
            setCalculatorNumber1("-"+getCalculatorNumber1());
            calculatorScreen.setText(calculatorNumber1);
        }
    }

    public void calcResult(){
        if (!getCalculatorNumber1().equals("")) {
            if (operation.equals("+")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                number1 = number1.add(bigDecimal);
                calculatorScreen.setText(number1.toString());
                setCalculatorNumber1(number1.toString());
                operation = "";
            } else if (operation.equals("-")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                number1 = number1.subtract(bigDecimal);
                calculatorScreen.setText(number1.toString());
                setCalculatorNumber1(number1.toString());
                operation = "";
            } else if (operation.equals("*")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                number1 = number1.multiply(bigDecimal);
                calculatorScreen.setText(number1.toString());
                setCalculatorNumber1(number1.toString());
                operation = "";
            } else if (operation.equals("/")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                try {
                    number1 = number1.divide(bigDecimal);
                } catch (ArithmeticException e) {
                    number1 = number1.divide(bigDecimal, 15, RoundingMode.HALF_DOWN);
                }
                calculatorScreen.setText(number1.toString());
                setCalculatorNumber1(number1.toString());
                operation = "";
            } else if (operation.equals("%")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                BigDecimal percent = BigDecimal.valueOf(100);
                try {
                    number1 = number1.divide(bigDecimal).multiply(percent);
                } catch (ArithmeticException e) {
                    MathContext mathContext = new MathContext(15);
                    number1 = number1.divide(bigDecimal, 17, RoundingMode.HALF_DOWN).multiply(percent).round(mathContext);
                }
                calculatorScreen.setText(number1.toString());
                setCalculatorNumber1(number1.toString());
                operation = "";
            }
        } else {
            operation="";
        }
    }

    //*****************************WSZYSTKO DLA POGODY*********************************:

    @FXML
    private Label weatherDataInput;

    @FXML
    private TextField cityName;

    @FXML
    public void checkWeatherButton() {
        if (cityName.getText().length()>0){
            String city= cityName.getText();
            cityName.setText("");
            Weather weather = new Weather();
            weather.setCity(city);

            try {
                weather.parseJson(weather.getResponse(weather.buildUrl()));
                String weatherInfo = new StringBuilder(weather.getName()).append(" ").append(weather.getTemperature()).append("\n").append(weather.getDescription()).toString();
                weatherDataInput.setText(weatherInfo);
            } catch (IOException e) {
                weatherDataInput.setText("Sorry something went wrong.\nWe couldn't find such a city :(");
            }
        }
    }

    //*****************************WSZYSTKO DLA KALENDARZA*********************************:

    private LocalDate todayDate;

    private LocalDate firstDay;

    @FXML
    private Label calendarMonthLabel;

    @FXML
    private GridPane calendarDaysGridPane;

    public void disableButton(int buttonNo, boolean choice){
        String idToCompare = new StringBuilder("dayCard").append(buttonNo).toString();
        for(Node n : calendarDaysGridPane.getChildren()){
            if(n instanceof Button){
                if(((Button)n).getId().equals(idToCompare)){
                    ((Button)n).setText("");
                    ((Button)n).setDisable(choice);
                }
            }
        }
    }

    public void setButtonText(int buttonNo, int day){
        String idToCompare = new StringBuilder("dayCard").append(buttonNo).toString();
        for(Node n : calendarDaysGridPane.getChildren()){
            if(n instanceof Button){
                if(((Button)n).getId().equals(idToCompare)){
                    ((Button)n).setDisable(false);
                    ((Button)n).setText(String.valueOf(day));
                }
            }
        }
    }

    public void setDatesToDisplay(int daysMonth, int thisYear, Month month, int weekdayAtStartOfMonth){
        for(int i=0; i<weekdayAtStartOfMonth-1; i++){
            disableButton(i,true);
        }
        int day=0;
        for(int i=weekdayAtStartOfMonth-1; i<daysMonth+weekdayAtStartOfMonth;i++){


            day++;
            setButtonText(i,day);

        }
        for (int i=weekdayAtStartOfMonth+daysMonth-1;i<42;i++){
            disableButton(i,true);
        }
        calendarMonthLabel.setText(month.toString());

    }

    @FXML
    public void prevMonthSwitch(){}

    @FXML
    public void nextMonthSwitch(){}

    @FXML
    public void setCalendarDaysGridPane(){
        int thisYear = todayDate.getYear();
        Month thisMonth = todayDate.getMonth();
        boolean leapYear = todayDate.isLeapYear();
        LocalDate firstDay = LocalDate.of(thisYear,thisMonth,1);
        DayOfWeek firstOfMonth =firstDay.getDayOfWeek();
        int weekdayAtStartOfMonth=firstOfMonth.getValue();
        int daysMonth = thisMonth.length(leapYear);
    }

    @FXML
    void initialize() {
        article1 = new Article();
        getHeadlines();
        number1=new BigDecimal(0);
//        *********KALENDARZ************:
//        List<Button> buttonsList = new LinkedList<>();
//        buttonsList.addAll(Arrays.asList(dayCard0,dayCard1,dayCard2,dayCard3,dayCard4,dayCard5,dayCard6,dayCard6,dayCard7,dayCard8,dayCard9,dayCard10,dayCard11,dayCard11,dayCard12,dayCard13,dayCard13,dayCard14,dayCard14,dayCard15,dayCard16,dayCard16,dayCard17,dayCard18,dayCard18,dayCard19,dayCard20,dayCard21,dayCard22));
        todayDate = LocalDate.now();
        int thisYear = todayDate.getYear();
        Month thisMonth = todayDate.getMonth();
        firstDay = LocalDate.of(thisYear,thisMonth,1);
        boolean leapYear = todayDate.isLeapYear();
        int daysMonth = thisMonth.length(leapYear);
        DayOfWeek firstOfMonth =firstDay.getDayOfWeek();
        int weekdayAtStartOfMonth=firstOfMonth.getValue();
//        ObservableList<Node> nodes;
//        nodes = calendarDaysGridPane.getChildren();
        setDatesToDisplay(daysMonth,thisYear,thisMonth,weekdayAtStartOfMonth);
//        for (Node n: nodes
//             ) {
//            if(n instanceof Button){
//                if(((Button) n).getId().equals("dayCard15")){
//                    ((Button) n).setText(todayDate.toString());
//                }
//            }
//        }
    }

//    @FXML
//    private Button dayCard0;
//
//    @FXML
//    private Button dayCard1;
//
//    @FXML
//    private Button dayCard2;
//
//    @FXML
//    private Button dayCard3;
//
//    @FXML
//    private Button dayCard4;
//
//    @FXML
//    private Button dayCard5;
//
//    @FXML
//    private Button dayCard6;
//
//    @FXML
//    private Button dayCard7;
//
//    @FXML
//    private Button dayCard8;
//
//    @FXML
//    private Button dayCard9;
//
//    @FXML
//    private Button dayCard10;
//
//    @FXML
//    private Button dayCard11;
//
//    @FXML
//    private Button dayCard12;
//
//    @FXML
//    private Button dayCard13;
//
//    @FXML
//    private Button dayCard14;
//
//    @FXML
//    private Button dayCard15;
//
//    @FXML
//    private Button dayCard16;
//
//    @FXML
//    private Button dayCard17;
//
//    @FXML
//    private Button dayCard18;
//
//    @FXML
//    private Button dayCard19;
//
//    @FXML
//    private Button dayCard20;
//
//    @FXML
//    private Button dayCard21;
//
//    @FXML
//    private Button dayCard22;
//
//    @FXML
//    private Button dayCard23;
//
//    @FXML
//    private Button dayCard24;
//
//    @FXML
//    private Button dayCard25;
//
//    @FXML
//    private Button dayCard26;
//
//    @FXML
//    private Button dayCard27;
//
//    @FXML
//    private Button dayCard28;
//
//    @FXML
//    private Button dayCard29;
//
//    @FXML
//    private Button dayCard30;
//
//    @FXML
//    private Button dayCard31;
//
//    @FXML
//    private Button dayCard32;
//
//    @FXML
//    private Button dayCard33;
//
//    @FXML
//    private Button dayCard34;
//
//    @FXML
//    private Button dayCard35;
//
//    @FXML
//    private Button dayCard36;
//
//    @FXML
//    private Button dayCard37;
//
//    @FXML
//    private Button dayCard38;
//
//    @FXML
//    private Button dayCard39;
//
//    @FXML
//    private Button dayCard40;
//
//    @FXML
//    private Button dayCard41;
}
