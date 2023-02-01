package importentQuestions;

import PageObject.PageAboutRent;
import PageObject.PageUserData;
import PageObject.ScooterOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test    // тестирование кнопок "заказать"
    public void checkingOrderButtons() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        ScooterOrder user = new ScooterOrder(driver);
        user.openPageBottomButton();
        user.openPageTopButton();
        Assert.assertEquals(user.openPageBottomButton(), user.openPageTopButton());
    }

    @Test  //проверка ответов "вопросы о важном"
    public void checkingAnswers() {
        ScooterOrder page = new ScooterOrder(driver);
        page.openPage();
        page.addCookies();
        page.scrollingPage();
        for (int i = 0; i < page.getExpectedAnswers().length; i++) {
            By QUESTIONS = By.xpath(".//div[@id='accordion__heading-" + i + "']");
            By ANSWER_TO_QUESTIONS = By.xpath(".//div[@aria-labelledby='accordion__heading-" + i + "']");
            driver.findElement(QUESTIONS).click();
            String text = driver.findElement(ANSWER_TO_QUESTIONS).getText();
            Assert.assertEquals(page.getExpectedAnswers()[i], text);
        }

    }

    @Test //тестирование страницы "PageUserData" по нижней кнопки "заказать"
    // Выбор станции через терминал
    public void pageUserDataTestBottomButtonStationSelectionViaTerminal() {
        ScooterOrder page = new ScooterOrder(driver);
        page.pageOpenAndAddCookiesBottomButton();
        PageUserData user = new PageUserData(driver);
        user.setUsername("Антон");
        user.setUserSurname("Миронов");
        user.setUserAddress("Чистова 12");
        user.setUserSubwayStationChoose();
        user.setUserPhone("89652365174");
        user.clickNextButton();

    }

    @Test //тестирование страницы "PageUserData" по верхней кнопки "заказать"
    public void pageUserDataTestTopButton() {
        ScooterOrder page = new ScooterOrder(driver);
        page.pageOpenAndAddCookiesTopButton();
        PageUserData user = new PageUserData(driver);
        user.setUsername("Анна");
        user.setUserSurname("Каренина");
        user.setUserAddress("Пушкинская 25");
        user.setUserSubwayStation("Фили");
        user.setUserPhone("89652365174");
        user.clickNextButton();

    }

    @Test //тестирование страницы "PageAboutRent"
    public void pageAboutRentTest() {
        ScooterOrder page = new ScooterOrder(driver);
        page.pageOpenAndAddCookiesBottomButton();
        PageUserData user = new PageUserData(driver);
        user.pageUserData("Ольга", "Игоревна",
                "Тушинская 45", "Братиславская", "89565352211");
        PageAboutRent user2 = new PageAboutRent(driver);
        user2.setData("30.06.2023");
        user2.setRentalPeriod3Days();
        user2.clickGreyButton();
        user2.setComment("Привет");
        user2.clickOrder();

    }

    @Test //тестирование страницы "PageAboutRent"
    // выбрать дату через терминал

    public void pageAboutRentTestSelectDateViaTerminal() {
        ScooterOrder page = new ScooterOrder(driver);
        page.pageOpenAndAddCookiesTopButton();
        PageUserData user = new PageUserData(driver);
        user.pageUserData("Ольга", "Игоревна",
                "Тушинская 45", "Киевская", "89565352211");
        PageAboutRent user2 = new PageAboutRent(driver);
        user2.setDataChoose();
        user2.setRentalPeriod7Days();
        user2.clickBlackButton();
        user2.setComment("Приятно было познакомиться");
        user2.clickOrder();

    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}

