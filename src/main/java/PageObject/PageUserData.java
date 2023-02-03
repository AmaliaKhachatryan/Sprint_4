package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUserData {
    public static final By NAME_LINE = By.xpath(".//input[@placeholder='* Имя']");
    public static final By SURNAME_LINE = By.xpath(".//input[@placeholder='* Фамилия']");
    public static final By ADDRESS_LINE = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By SUBWAY_STATION_LINE = By.xpath(".//input[@placeholder='* Станция метро']");
    public static final By SUBWAY_STATION_SELECTOR = By.xpath(".//li[@data-index='0']");
    public static final By TELEPHONE_LINE = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    public static final By NEXT_BUTTON = By.xpath(".//button[(text()='Далее')]");
    private static final By DELIVERY_DATE_LINE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By STATION_TVERSKAYA=By.xpath(".//div[text()='Тверская']");
    private WebDriver driver;

    public PageUserData(WebDriver driver) {
        this.driver = driver;
    }

    public PageUserData setUsername(String name) {
        driver.findElement(NAME_LINE).sendKeys(name);
        return this;

    }

    public PageUserData setUserSurname(String surname) {
        driver.findElement(SURNAME_LINE).sendKeys(surname);
        return this;
    }

    public PageUserData setUserAddress(String address) {
        driver.findElement(ADDRESS_LINE).sendKeys(address);
        return this;
    }

    public PageUserData setUserSubwayStation(String subwayStation) {
        driver.findElement(SUBWAY_STATION_LINE).sendKeys(subwayStation);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBWAY_STATION_SELECTOR));
        driver.findElement(SUBWAY_STATION_SELECTOR).click();
        return this;
    }

    public PageUserData setUserChooseSubwayStationTverskaya() {
        driver.findElement(SUBWAY_STATION_LINE).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(SUBWAY_STATION_SELECTOR));
        WebElement element = driver.findElement(STATION_TVERSKAYA);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(STATION_TVERSKAYA).click();
        return this;
    }

    public PageUserData setUserPhone(String phone) {
        driver.findElement(TELEPHONE_LINE).sendKeys(phone);
        return this;
    }

    public PageUserData clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
        return this;
    }

        public boolean deliveryDataLineIsClickable(){
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(DELIVERY_DATE_LINE));
            return  driver.findElement(DELIVERY_DATE_LINE).isDisplayed();

    }

    public PageUserData pageUserData(String name, String surname, String address,
                                     String subwayStation, String phone) {
        setUsername(name);
        setUserSurname(surname);
        setUserAddress(address);
        setUserSubwayStation(subwayStation);
        setUserPhone(phone);
        clickNextButton();
        return this;

    }

    public PageUserData pageUserDataChooseStationTverskaya(String name, String surname, String address, String phone) {
        setUsername(name);
        setUserSurname(surname);
        setUserAddress(address);
        setUserChooseSubwayStationTverskaya();
        setUserPhone(phone);
        clickNextButton();
        return this;

    }
}
