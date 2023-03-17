package ru.yandex.practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class statusPageObjectModel {
    private WebDriver driver;
    private By notFoundMessageImg = By.xpath(".//img[@src= '/assets/not-found.png']");
    public statusPageObjectModel(WebDriver driver){
        this.driver = driver;
    }
    public void notFoundMessageImgIsDisplayed(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(notFoundMessageImg)));
        assertTrue("Сообщение с ошибкой не отобразилось", driver.findElement(notFoundMessageImg).isDisplayed());
    }
}
