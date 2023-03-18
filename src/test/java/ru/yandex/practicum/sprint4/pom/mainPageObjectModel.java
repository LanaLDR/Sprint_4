package ru.yandex.practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class mainPageObjectModel {
    private WebDriver driver;
    public mainPageObjectModel(WebDriver driver){
        this.driver = driver;
    }
    private By logoHeaderLinks = By.xpath(".//a[contains(@class, 'Header_Logo')]");
    private By orderButton = By.xpath(".//button[text() = 'Заказать']");
    private By headerButtonStatusOrder = By.xpath(".//button[@class = 'Header_Link__1TAG7']");
    private By headerStatusOrderInput = By.xpath(".//input[@placeholder = 'Введите номер заказа']");
    private By pageStatusOrderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text() = 'Go!']");
    private By cookieConfirmButton = By.xpath(".//button[text()='да все привыкли']");
    private By accordionButtons = By.xpath(".//div[@class = 'accordion__button']");
    private By textInAccordion = By.xpath(".//div[@class = 'accordion__panel']");

    public void logoHeaderLinkClick(int index){
        driver.findElements(logoHeaderLinks).get(index).click();
    }
    public void headerButtonStatusOrderClick(){
        driver.findElement(headerButtonStatusOrder).click();
    }
    private void setHeaderStatusOrderInput(String text){
        driver.findElement(headerStatusOrderInput).sendKeys(text);
    }

    private void HeaderStatusOrderInputIsDisplayed(){
        new WebDriverWait(driver, 50)
                .until(ExpectedConditions.visibilityOf(driver.findElement(headerStatusOrderInput)));
        assertTrue(driver.findElement(headerStatusOrderInput).isDisplayed());
    }
    private void pageStatusOrderButtonClick(){
        driver.findElement(pageStatusOrderButton).click();
    }
    public void orderButtonClick(int index){
        driver.findElements(orderButton).get(index).click();
    }
    public void cookieConfirmButtonClick(){
        driver.findElement(cookieConfirmButton).click();
    }
    public void accordionButtonClick(int index){
        driver.findElements(accordionButtons).get(index).click();
    }

    public void scrollToAccordionButton(int index) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElements(accordionButtons).get(index));
        new WebDriverWait(driver, 50)
                .until(ExpectedConditions.visibilityOf(driver.findElements(accordionButtons).get(index)));
    }
    public void textInAccordionIsVisible(int index){
        new WebDriverWait(driver, 50)
                .until(ExpectedConditions.visibilityOf(driver.findElements(textInAccordion).get(index)));
        assertTrue(driver.findElements(textInAccordion).get(index).isDisplayed());
    }

    public String getTextInAccordion(int index){
        return driver.findElements(textInAccordion).get(index).getText();
    }

    public void accordionTextEqualExpectedText(String textInAccordion, int index){
        assertTrue("Текст в аккордионе не равен ожидаемому", getTextInAccordion(index).equals(textInAccordion));
    }

    public void accordionButtonOpenAndShowEqualText(int index, String textInAccordion){
        scrollToAccordionButton(index);
        accordionButtonClick(index);
        textInAccordionIsVisible(index);
        accordionTextEqualExpectedText(textInAccordion, index);
    }
    public void openOrderStatusPage(String text){
        headerButtonStatusOrderClick();
        HeaderStatusOrderInputIsDisplayed();
        setHeaderStatusOrderInput(text);
        pageStatusOrderButtonClick();
    }
}
