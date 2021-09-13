package tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages_objects.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

@Slf4j
public class MainPageTests {

    @Before
    public void setup() {
        open("https://mail.ru/");
        log.info("Переход на страницу осуществлен");
    }

    @Test
    public void checkPageFirst() {
        String login = "mailLogin";
        String password = "qwerty123";
        $(By.xpath("//input[@name='login']")).shouldBe(visible);
        $(By.id("grid")).shouldBe(visible);
        $(By.name("login")).shouldBe(visible).sendKeys(login);
//        sleep(5000);
        $x("//button[@data-testid='enter-password']").shouldBe(visible).click();
        String email = $(By.name("clb36299772")).shouldBe(visible).getText();
        assertEquals("email отобразился неверно", login + "@mail.ru", email);
        $(By.name("password")).shouldBe(visible).sendKeys(password);
//        sleep(5000);
        $x("//button[@data-testid='login-to-mail']").shouldBe(visible).click();
        $x("//div[contains(@class,'error ')]").shouldHave(text("Неверное имя или пароль"));
    }

    @Test
    public void checkPageSecond() {
        String login = "mailLogin";
        String password = "qwerty123";
        $("input[name=login]").shouldBe(visible);
        $("#grid").shouldBe(visible);
        $("input[name=login]").shouldBe(visible).sendKeys(login);
//        sleep(5000); можно включить, если тест проходит слишком быстро
        $("button[data-testid=enter-password]").shouldBe(visible).click();
        String email = $("div[name=clb36299772]").shouldBe(visible).getText();
        assertEquals("email отобразился неверно", login + "@mail.ru", email);
        $("input[name=password]").shouldBe(visible).sendKeys(password);
//        sleep(5000);
        $("button[data-testid=login-to-mail]").shouldBe(visible).click();
        $(".error").shouldHave(text("Неверное имя или пароль"));
    }


    //todo здесь можно посмотреть примеры allure отчета https://demo.qameta.io/allure/#suites/bd5db97a62d5115f1a4b09738ac3c2b1/c9fca23c1efeb966/
    @Test
    public void checkPageThree() {
        String login = "mailLogin";
        String password = "qwerty123";
        new MainPage()
                .smokeCheckPage()
                .enterLoginAndClickButton(login)
                .checkDisplayEmail(login)
                .enterPasswordAndClick(password)
                .checkAlertIncorrectLoginOrPass();
    }

}
