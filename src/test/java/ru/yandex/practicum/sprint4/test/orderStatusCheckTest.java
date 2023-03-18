package ru.yandex.practicum.sprint4.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.sprint4.pom.mainPageObjectModel;
import ru.yandex.practicum.sprint4.pom.statusPageObjectModel;

import static org.junit.Assert.assertTrue;

public class orderStatusCheckTest {
    private WebDriver driver;

    private final String text = "amogus";

    public orderStatusCheckTest(){
    }

    @BeforeClass
    public static void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupDriver(){
        driver = new ChromeDriver();
    }

    @Test
    public void badOrderNumberGetNotFoundMessage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPageObjectModel mainPageObject = new mainPageObjectModel(driver);
        statusPageObjectModel statusPageObject = new statusPageObjectModel(driver);
        mainPageObject.openOrderStatusPage(text);
        statusPageObject.notFoundMessageImgIsDisplayed();
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
