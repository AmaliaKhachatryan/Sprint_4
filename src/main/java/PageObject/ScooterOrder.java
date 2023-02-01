package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ScooterOrder {

    private static final String MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";
    private static final String SCOOTER_ORDER_PAGE = "https://qa-scooter.praktikum-services.ru/order";
    private static final By MAIN_BUTTON_ORDER_UP = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static final By MAIN_BUTTON_ORDER_DOWN = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By CLICK_COOKIE = By.xpath(".//button[@class='App_CookieButton__3cvqF']");
    private static String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static By LIST_OF_QUESTIONS = By.xpath(".//*[@class='accordion__item']");
    private String[] expectedAnswers = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };
    private WebDriver driver;

    public ScooterOrder(WebDriver driver) {
        this.driver = driver;
    }

    public String[] getExpectedAnswers() {
        return expectedAnswers;
    }

    public void setExpectedAnswers(String[] expectedAnswers) {
        this.expectedAnswers = expectedAnswers;
    }

    public ScooterOrder openPage() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get(PAGE_URL);
        return this;
    }

    public ScooterOrder addCookies() {
        driver.findElement(CLICK_COOKIE).click();
        Cookie newCookies = new Cookie("first_cookie", "10");
        driver.manage().addCookie(newCookies);
        return this;
    }

    public ScooterOrder scrollingPage() {
        WebElement element = driver.findElement(LIST_OF_QUESTIONS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public ScooterOrder openPageTopButton() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get(MAIN_PAGE);
        driver.findElement(MAIN_BUTTON_ORDER_UP).click();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        wait1.until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/order"));

        return this;
    }

    public ScooterOrder openPageBottomButton() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get(MAIN_PAGE);
        WebElement element = driver.findElement(MAIN_BUTTON_ORDER_DOWN);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(MAIN_BUTTON_ORDER_DOWN).click();

        return this;
    }

    public ScooterOrder pageOpenAndAddCookiesTopButton() {
        openPageTopButton();
        addCookies();
        return this;
    }

    public ScooterOrder pageOpenAndAddCookiesBottomButton() {
        openPageBottomButton();
        addCookies();
        return this;
    }
}





