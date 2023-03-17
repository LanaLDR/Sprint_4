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

@RunWith(Parameterized.class)

public class accordionTest {
    private WebDriver driver;

    private final int index;
    private final String textInAccordion;

    public accordionTest(int index, String textInAccordion){
        this.index = index;
        this.textInAccordion = textInAccordion;
    }

    @Parameterized.Parameters
    public static Object[][] getAccordionElementInfo(){
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
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
    public void equalsTextInAccordion() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPageObjectModel mainPageObject = new mainPageObjectModel(driver);
        mainPageObject.cookieConfirmButtonClick();
        mainPageObject.accordionButtonOpenAndShowEqualText(index, textInAccordion);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
