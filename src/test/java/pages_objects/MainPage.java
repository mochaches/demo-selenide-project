package pages_objects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.Assert.assertEquals;

@Slf4j
public class MainPage {
    private final static String LOGIN_INPUT_NAME = "login";
    private final static String PASSWORD_INPUT_NAME = "password";
    private final static String ENTER_LOGIN_BUTTON = "//button[@data-testid='enter-password']";
    private final static String ENTER_PASSWORD_BUTTON = "//button[@data-testid='login-to-mail']";
    private final static String ALERT_LOGIN_OR_PASS_ERROR = "//div[contains(@class,'error ')]";

    private final static SelenideElement CHECK_EMAIL = $(By.name("clb36299772"));

    @Step("Проверили отображение основных элементов")
    public MainPage smokeCheckPage() {
        $(By.name(LOGIN_INPUT_NAME)).shouldBe(visible);
        log.info("Поле ввода логина отображено");
        $x(ENTER_LOGIN_BUTTON).shouldBe(visible);
        log.info("Кнопка подтверждения логина отображена");
        return this;
    }

    @Step("Убедились, что email отобразился верно")
    public MainPage checkDisplayEmail(String login) {
        String email = CHECK_EMAIL.shouldBe(visible).getText();
        assertEquals("email отобразился неверно", login + "@mail.ru", email);
        log.info("email отобразился корректно");
        return this;
    }

    @Step("Вводим логин и жмем кнопку подтверждения")
    public MainPage enterLoginAndClickButton(String login) {
        $(By.name(LOGIN_INPUT_NAME)).shouldBe(visible).sendKeys(login);
        log.info("Ввели логин");
        $x(ENTER_LOGIN_BUTTON).shouldBe(visible).click();
        log.info("Нажали кнопку подтверждения логина");
        return this;
    }

    @Step("Вводим пароль и жмем кнопку подтверждения")
    public MainPage enterPasswordAndClick(String password) {
        $(By.name(PASSWORD_INPUT_NAME)).shouldBe(visible).sendKeys(password);
        log.info("Ввели пароль");
        $x(ENTER_PASSWORD_BUTTON).shouldBe(visible).click();
        log.info("Нажали кнопку подтверждения пароля");
        return this;
    }

    @Step("Проверяем корректность отображения сообщения об ошибке")
    public MainPage checkAlertIncorrectLoginOrPass() {
        $x(ALERT_LOGIN_OR_PASS_ERROR).shouldHave(text("Неверное имя или пароль"));
        log.info("Сообщение обо ошибке отображено корректно");
        return this;
    }
}
