package importentQuestions;

import PageObject.PageAboutRent;
import PageObject.PageUserData;
import PageObject.ScooterOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

//
@RunWith(Parameterized.class)
public class ParameterizedTest {
    private final String userName;
    private final String userSurname;
    private final String userAddress;
    private final String userSubwayStation;
    private final String userPhone;
    private String userDeliveryDay;
    private String userComment;


    public ParameterizedTest(String userName, String userSurname, String userAddress,
                             String userSubwayStation, String userPhone, String userDeliveryDay,
                             String userComment) {
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
                {"Милена", "Геворгян", "Шкулева дом 3", "Волжская", "+9658256543", "03.02.2023", "Привет"},
                {"Анастасия", "Миронова", "Малышева дом 6", "Текстильщики", "+96558547216", "05.01.2023", "Пока"}
        };
    }

    @Test

    public void pageAboutRentTest() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        ScooterOrder user = new ScooterOrder(driver);
        user.pageOpenAndAddCookiesBottomButton();
        PageUserData user1 = new PageUserData(driver);
        user1.pageUserData(userName, userSurname, userAddress, userSubwayStation, userPhone);
        PageAboutRent user2 = new PageAboutRent(driver);
        user2.pageAboutRent(userDeliveryDay, userComment);
        boolean isDisplayed = driver.findElement(By.xpath
                (".//*[contains(text(),'Заказ оформлен')]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        driver.quit();

    }
}







