package OrderAScooter;

import PageObject.ScooterOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class ListOfQuestionsTest {
    private static final By LIST_OF_QUESTIONS = By.xpath(".//*[@class='accordion__item']");
    private static final By QUESTIONS_1 = By.xpath(".//div[@id='accordion__heading-0']");//Сколько это стоит? И как оплатить?
    private static final By QUESTIONS_2 = By.xpath(".//div[@id='accordion__heading-1']");//Хочу сразу несколько самокатов! Так можно?
    private static final By QUESTIONS_3 = By.xpath(".//div[@id='accordion__heading-2']");//Как рассчитывается время аренды?
    private static final By QUESTIONS_4 = By.xpath(".//div[@id='accordion__heading-3']");//Можно ли заказать самокат прямо на сегодня?
    private static final By QUESTIONS_5 = By.xpath(".//div[@id='accordion__heading-4']");//Можно ли продлить заказ или вернуть самокат раньше?
    private static final By QUESTIONS_6 = By.xpath(".//div[@id='accordion__heading-5']");//Вы привозите зарядку вместе с самокатом??
    private static final By QUESTIONS_7 = By.xpath(".//div[@id='accordion__heading-6']");//Можно ли отменить заказ?(".//div[@aria-disabled='true']")
    private static final By QUESTIONS_8 = By.xpath(".//div[@id='accordion__heading-7']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_1 = By.xpath(".//div[@aria-labelledby='accordion__heading-0']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_2 = By.xpath(".//div[@aria-labelledby='accordion__heading-1']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_3 = By.xpath(".//div[@aria-labelledby='accordion__heading-2']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_4 = By.xpath(".//div[@aria-labelledby='accordion__heading-3']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_5 = By.xpath(".//div[@aria-labelledby='accordion__heading-4']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_6 = By.xpath(".//div[@aria-labelledby='accordion__heading-5']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_7 = By.xpath(".//div[@aria-labelledby='accordion__heading-6']");//Я жизу за МКАДом, привезёте?
    private static final By ANSWER_TO_QUESTIONS_8 = By.xpath(".//div[@aria-labelledby='accordion__heading-7']");//Я жизу за МКАДом, привезёте?


    private WebDriver driver;

    private By question;
    private By answer;
    private  String expected;

        public ListOfQuestionsTest(By question, By answer, String expected) {
        this.question = question;
        this.answer = answer;
        this.expected = expected;
    }
    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{{QUESTIONS_1, ANSWER_TO_QUESTIONS_1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {QUESTIONS_2, ANSWER_TO_QUESTIONS_2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {QUESTIONS_3, ANSWER_TO_QUESTIONS_3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {QUESTIONS_4, ANSWER_TO_QUESTIONS_4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {QUESTIONS_5, ANSWER_TO_QUESTIONS_5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {QUESTIONS_6, ANSWER_TO_QUESTIONS_6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {QUESTIONS_7, ANSWER_TO_QUESTIONS_7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {QUESTIONS_8, ANSWER_TO_QUESTIONS_8,"Да, обязательно. Всем самокатов! И Москве, и Московской области."}

        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }
    @Test
    public void listOfQuestionsTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ScooterOrder page = new ScooterOrder(driver);
        page.openPageAddCookies();
        WebElement element = driver.findElement(LIST_OF_QUESTIONS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(question).click();
        String actual = driver.findElement(answer).getText();
        Assert.assertEquals(expected, actual);

    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}


