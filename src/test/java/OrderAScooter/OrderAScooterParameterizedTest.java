package OrderAScooter;

import PageObject.PageAboutRent;
import PageObject.PageUserData;
import PageObject.ScooterOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

//
@RunWith(Parameterized.class)
public class OrderAScooterParameterizedTest {
    private static final By MAIN_BUTTON_ORDER_TOP = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static final By MAIN_BUTTON_ORDER_BOTTOM = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By buttonOrderMain;
    private String userName;
    private String userSurname;
    private String userAddress;
    private String userSubwayStation;
    private String userPhone;
    private String userDeliveryDay;
    private String userComment;


    public OrderAScooterParameterizedTest(By buttonOrderMain, String userName, String userSurname, String userAddress,
                                          String userSubwayStation, String userPhone, String userDeliveryDay,
                                          String userComment) {
        this.buttonOrderMain = buttonOrderMain;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.userSubwayStation = userSubwayStation;
        this.userPhone = userPhone;
        this.userDeliveryDay = userDeliveryDay;
        this.userComment = userComment;

    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {MAIN_BUTTON_ORDER_TOP, "Милена", "Геворгян", "Шкулева дом 3", "Волжская", "+9658256543", "03.02.2023", "Привет"},
                {MAIN_BUTTON_ORDER_BOTTOM, "Анастасия", "Миронова", "Малышева дом 6", "Текстильщики", "+96558547216", "05.01.2023", "Пока"}
        };
    }

    private WebDriver driver;

    @Before
//    public void setUp() {
//        driver = new FirefoxDriver();
//    }
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test

    public void OrderAScooterTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ScooterOrder user = new ScooterOrder(driver);
        user.openPageAddCookiesClickOnButton(buttonOrderMain);
        PageUserData user1 = new PageUserData(driver);
        user1.pageUserData(userName, userSurname, userAddress, userSubwayStation, userPhone);
        PageAboutRent user2 = new PageAboutRent(driver);
        user2.pageAboutRent(userDeliveryDay, userComment);
        Assert.assertTrue(user2.orderProcessedisDisplay());

    }
    @Test

    public void OrderAScooterChooseDataTodayTestChooseStationTverskayaTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ScooterOrder user = new ScooterOrder(driver);
        user.openPageAddCookiesClickOnButton(buttonOrderMain);
        PageUserData user1 = new PageUserData(driver);
        user1.pageUserDataChooseStationTverskaya(userName, userSurname, userAddress, userPhone);
        PageAboutRent user2 = new PageAboutRent(driver);
        user2.pageAboutRentChooseDataToday(userComment);
        Assert.assertTrue(user2.orderProcessedisDisplay());

    }

    @Test
    public void pageUserDataTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ScooterOrder page = new ScooterOrder(driver);
        page.openPageAddCookiesClickOnButton(buttonOrderMain);
        PageUserData user = new PageUserData(driver);
        user.pageUserData(userName, userSurname, userAddress, userSubwayStation, userPhone);
        Assert.assertTrue(user.deliveryDataLineIsClickable());

    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}








