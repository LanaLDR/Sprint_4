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
import ru.yandex.practicum.sprint4.pom.mainPageObjectModel;
import ru.yandex.practicum.sprint4.pom.orderPageObjectModel;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class mainPageLinksTest {

    private WebDriver driver;
    private int index;
    private String link;


    public mainPageLinksTest(int index, String link){
        this.index = index;
        this.link = link;
    }

    @Parameterized.Parameters
    public static Object[][] getMainPageLinksInfo(){
        return new Object[][]{
                {0, "https://dzen.ru/?yredirect=true"},
                {1, "https://qa-scooter.praktikum-services.ru/"},
        };
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
    public void successfulClickOnLinks() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPageObjectModel mainPageObject = new mainPageObjectModel(driver);
        mainPageObject.logoHeaderLinkClick(index);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        if (tabs.size() > 1) { driver.switchTo().window(tabs.get(1)); }
        assertTrue("Открылась некорректная страница", driver.getCurrentUrl().equals(link));
    }
    @After
    public void tearDown(){
        driver.quit();
    }

}
