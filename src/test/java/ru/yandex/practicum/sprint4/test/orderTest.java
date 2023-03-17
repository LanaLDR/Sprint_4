package ru.yandex.practicum.sprint4.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.sprint4.pom.mainPageObjectModel;
import ru.yandex.practicum.sprint4.pom.orderPageObjectModel;


@RunWith(Parameterized.class)

public class orderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;
    private final int index;
    public orderTest(int index, String name, String surname, String address, String metro, String phoneNumber, String date, String rentalPeriod, String color, String comment){
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] getOrderInfo(){
        return new Object[][]{
                {1, "Кирилл", "Иванов", "Колотушкина 15", "Бульвар Рокоссовского", "889803628933", "15.03.2023", "трое суток", "grey", "хочется спать"},
                {2, "Амогус", "Кириллов", "Хогвартс 1", "Черкизовская", "88005553434", "20.08.2024", "сутки", "black", "не звонить!"},
        };
    }
    @BeforeClass
    public static void startUp() {
        //Баг при подтверждении заказа в хроме.
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupDriver(){
        driver = new ChromeDriver();
    }

    @Test
    public void successfulOrder() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPageObjectModel mainPageObject = new mainPageObjectModel(driver);
        orderPageObjectModel orderPageObject = new orderPageObjectModel(driver);
        mainPageObject.cookieConfirmButtonClick();
        mainPageObject.orderButtonClick(index);
        orderPageObject.fillingInTheOrderFields(name, surname, address, metro, phoneNumber, date, rentalPeriod, color, comment);
        orderPageObject.orderButtonClick();
        orderPageObject.modalConfirmOrderIsDisplayed();
        orderPageObject.submitOrderButtonClick();
        orderPageObject.checkStatusButtonIsDisplayed();
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
