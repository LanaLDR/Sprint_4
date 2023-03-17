package ru.yandex.practicum.sprint4.pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class orderPageObjectModel {
    private WebDriver driver;
    private By name = By.xpath(".//input[@placeholder = '* Имя']");
    private By surname = By.xpath(".//input[@placeholder = '* Фамилия']");
    private By address = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    private By metro = By.xpath(".//input[@placeholder = '* Станция метро']");
    private By phoneNumber = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath(".//button[text() = 'Далее']");
    private By date = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    private By rentalPeriodMenu = By.xpath(".//span[@class = 'Dropdown-arrow']");
    private By colorBlack = By.xpath(".//input[@id = 'black']");
    private By colorGrey = By.xpath(".//input[@id = 'grey']");
    private By comment = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    private By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']//button[text() = 'Заказать']");
    private By submitOrderButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Да']");
    private By checkStatusButton = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']//button[text()='Посмотреть статус']");
    private By orderModal = By.xpath(".//div[contains(@class, 'Order_Modal')]");

    public orderPageObjectModel(WebDriver driver){
        this.driver = driver;
    }

    public void setName(String name){
        driver.findElement(this.name).clear();
        driver.findElement(this.name).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(this.surname).clear();
        driver.findElement(this.surname).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(this.address).clear();
        driver.findElement(this.address).sendKeys(address);
    }

    public void setMetro(String metro){
        driver.findElement(this.metro).clear();
        driver.findElement(this.metro).sendKeys(metro);
    }

    public void clickMetroSelect(String metro) {
        driver.findElement(
                By.xpath(".//div[contains(@class, 'Order_Content')]//div[contains(@class, 'Order_Text') and text()='" + metro + "']")
        ).click();
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(this.phoneNumber).clear();
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
    }

    public void setDate(String date) {
        driver.findElement(this.date).sendKeys(date);
    }

    public void setRentalPeriod(String option) {
        driver.findElement(rentalPeriodMenu).click();
        driver.findElement(By.xpath(".//div[text() = '" + option + "']")).click();
    }

    public void setColor(String color) {
        if (color.equals("black")){
            driver.findElement(colorBlack).click();
        } else {
            driver.findElement(colorGrey).click();
        }
    }

    public void setComment(String comment){
        driver.findElement(this.comment).sendKeys(comment);
    }

    public void nextButtonClick(){
        driver.findElement(nextButton).click();
    }

    public void orderButtonClick() {
        driver.findElement(orderButton).click();
    }
    public void submitOrderButtonClick(){
        driver.findElement(submitOrderButton).click();
    }

    public void modalConfirmOrderIsDisplayed(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderModal)));
    }
    public void checkStatusButtonIsDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOf(driver.findElement(submitOrderButton)));
        assertTrue(driver.findElement(checkStatusButton).isDisplayed());
    }

    //Шаг
    public void fillingInTheOrderFields(String name, String surname, String address, String metro, String phoneNumber, String date, String rentalPeriod, String color, String comment){
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metro);
        clickMetroSelect(metro);
        setPhoneNumber(phoneNumber);
        nextButtonClick();
        setDate(date);
        setRentalPeriod(rentalPeriod);
        setColor(color);
        setComment(comment);
    }
}
