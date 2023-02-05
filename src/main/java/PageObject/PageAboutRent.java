package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAboutRent {
    private static final By DELIVERY_DATE_LINE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By DELIVERY_DATE_SELECTOR = By.xpath(".//div[contains(@class,'react-datepicker__day--selected')]");
    private static final By RENTAL_PERIOD_LINE = By.xpath(".//div[@class='Dropdown-placeholder']");//СРОКИ АРЕНДЫ
    private static final By RENTAL_PERIOD_MENU = By.xpath(".//div[@class='Dropdown-menu']");
    private static final By RENTAL_PERIOD_3DAY = By.xpath(".//div[text()='трое суток']");
    private static final By CHOOSE_RENTAL_PERIOD_7DAYS = By.xpath(".//div[text()='семеро суток']");
    private static final By BUTTON_BLACK_COLOR_SCOOTER = By.xpath(".//input[@id='black']");
    private static final By BUTTON_GREY_COLOR_SCOOTER = By.xpath(".//input[@id='grey']");
    private static final By COMMENTS_LINE = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private static final By BUTTON_ORDER_APPROVAL = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");//кнопка "заказать" для утверждения заказа
    private static final By YES_ORDER = By.xpath(".//button[(text()='Да')]");
    private static final By ORDER_PROCESSED = By.xpath((".//*[contains(text(),'Заказ оформлен')]"));
    private static final By CHOOSE_TODAY = By.xpath(".//div[contains(@class,'--keyboard-selected react-datepicker__day--today')]");
    private WebDriver driver;

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

    public PageAboutRent setDataChooseToday() {
        driver.findElement(DELIVERY_DATE_LINE).click();
        driver.findElement(CHOOSE_TODAY).click();
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_BLACK_COLOR_SCOOTER));
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
        return this;
    }

    public boolean orderProcessedisDisplay() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(ORDER_PROCESSED));
        return driver.findElement(ORDER_PROCESSED).isDisplayed();
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

    public PageAboutRent pageAboutRentChooseDataToday(String comment) {
        setDataChooseToday();
        setRentalPeriod3Days();
        clickGreyButton();
        setComment(comment);
        clickOrder();
        clickYes();
        return this;
    }

}