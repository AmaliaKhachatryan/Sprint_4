package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAboutRent {
    private static By DELIVERY_DATE_LINE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static By DELIVERY_DATE_SELECTOR = By.xpath(".//div[contains(@class,'react-datepicker__day--selected')]");
    private static By RENTAL_PERIOD_LINE = By.xpath(".//div[@class='Dropdown-placeholder']");//СРОКИ АРЕНДЫ
    private static By RENTAL_PERIOD_MENU = By.xpath(".//div[@class='Dropdown-menu']");
    private static By RENTAL_PERIOD_3DAY = By.xpath(".//div[text()='трое суток']");
    private static By CHOOSE_RENTAL_PERIOD_7DAYS = By.xpath(".//div[text()='семеро суток']");
    private static By BUTTON_BLACK_COLOR_SCOOTER = By.xpath(".//input[@id='black']");
    private static By BUTTON_GREY_COLOR_SCOOTER = By.xpath(".//input[@id='grey']");
    private static By COMMENTS_LINE = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private static By BUTTON_ORDER_APPROVAL = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");//кнопка "заказать" для утверждения заказа
    private static By YES_ORDER = By.xpath(".//button[(text()='Да')]");
    private static By ORDER_PROCESSED = By.xpath((".//*[contains(text(),'Заказ оформлен')]"));
    public WebDriver driver;

    public PageAboutRent(WebDriver driver) {
        this.driver = driver;
    }

    public PageAboutRent setData(String data) {
        driver.findElement(DELIVERY_DATE_LINE).sendKeys(data);
        driver.findElement(DELIVERY_DATE_SELECTOR).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELIVERY_DATE_LINE));
        return this;
    }

    public PageAboutRent setDataChoose() {
        driver.findElement(DELIVERY_DATE_LINE).click();
        driver.findElement(By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--010']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(RENTAL_PERIOD_LINE));
        return this;
    }

    public PageAboutRent setRentalPeriod3Days() {
        driver.findElement(RENTAL_PERIOD_LINE).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(RENTAL_PERIOD_3DAY));
        driver.findElement(RENTAL_PERIOD_3DAY).click();
        return this;

    }

    public PageAboutRent setRentalPeriod7Days() {
        driver.findElement(RENTAL_PERIOD_LINE).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(RENTAL_PERIOD_MENU));
        WebElement element = driver.findElement(CHOOSE_RENTAL_PERIOD_7DAYS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(CHOOSE_RENTAL_PERIOD_7DAYS).click();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_BLACK_COLOR_SCOOTER));
        return this;

    }

    public PageAboutRent clickBlackButton() {
        driver.findElement(BUTTON_BLACK_COLOR_SCOOTER).click();
        return this;
    }

    public PageAboutRent clickGreyButton() {
        driver.findElement(BUTTON_GREY_COLOR_SCOOTER).click();
        return this;
    }

    public PageAboutRent setComment(String comment) {
        driver.findElement(COMMENTS_LINE).sendKeys(comment);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_ORDER_APPROVAL));
        return this;
    }

    public PageAboutRent clickOrder() {
        driver.findElement(BUTTON_ORDER_APPROVAL).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(YES_ORDER));
        return this;
    }

    public PageAboutRent clickYes() {
        driver.findElement(YES_ORDER).click();
        driver.findElement(ORDER_PROCESSED);
        return this;
    }

    public PageAboutRent pageAboutRent(String data, String comment) {
        setData(data);
        setRentalPeriod7Days();
        clickBlackButton();
        setComment(comment);
        clickOrder();
        clickYes();
        return this;
    }

}