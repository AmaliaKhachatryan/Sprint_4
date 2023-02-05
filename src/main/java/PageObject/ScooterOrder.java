package PageObject;


import org.openqa.selenium.*;
import java.util.concurrent.TimeUnit;

public class ScooterOrder {

    private static final String MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";
    private static final By CLICK_COOKIE = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    private WebDriver driver;

    public ScooterOrder(WebDriver driver) {
        this.driver = driver;
    }

    public ScooterOrder openPage() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(MAIN_PAGE);
        return this;
    }

    public ScooterOrder addCookies() {
        driver.findElement(CLICK_COOKIE).click();
        Cookie newCookies = new Cookie("first_cookie", "10");
        driver.manage().addCookie(newCookies);
        return this;
    }

    public ScooterOrder openPageButton(By button) {
        WebElement element = driver.findElement(button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(button).click();
        return this;
    }

    public void openPageAddCookiesClickOnButton(By button) {
        openPage();
        addCookies();
        openPageButton(button);
    }

    public void openPageAddCookies() {
        openPage();
        addCookies();
    }
}