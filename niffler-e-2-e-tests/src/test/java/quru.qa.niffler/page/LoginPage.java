package quru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class LoginPage {
    private final SelenideElement  usernameInput = $("input[name='username']");
    private final SelenideElement  passwordInput = $("input[name='password']");
    private final SelenideElement  submitButton = $("button[type='submit']");

    public MainPage doLogin(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        sleep(1000);
        submitButton.click();

        return new MainPage();
    }
}
