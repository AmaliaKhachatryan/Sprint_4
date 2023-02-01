package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUserData {
    public static By NAME_LINE = By.xpath(".//input[@placeholder='* Имя']");
    public static By SURNAME_LINE = By.xpath(".//input[@placeholder='* Фамилия']");
    public static By ADDRESS_LINE = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    public static By SUBWAY_STATION_LINE = By.xpath(".//input[@placeholder='* Станция метро']");
    public static By SUBWAY_STATION_SELECTOR = By.xpath(".//li[@data-index='0']");
    public static By TELEPHONE_LINE = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    public static By NEXT_BUTTON = By.xpath(".//button[(text()='Далее')]");

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

    public PageUserData setUserSubwayStationChoose() {
        driver.findElement(SUBWAY_STATION_LINE).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(SUBWAY_STATION_SELECTOR));
        WebElement element = driver.findElement(By.xpath(".//div[text()='Тверская']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath(".//div[text()='Тверская']")).click();
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

    public PageUserData pageUserDataChooseStation(String name, String surname, String address, String phone) {
        setUsername(name);
        setUserSurname(surname);
        setUserAddress(address);
        setUserSubwayStationChoose();
        setUserPhone(phone);
        clickNextButton();
        return this;

    }
}
