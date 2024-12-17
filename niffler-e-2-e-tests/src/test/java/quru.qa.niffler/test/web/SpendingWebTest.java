package quru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import quru.qa.niffler.config.Config;
import quru.qa.niffler.jupiter.Spend;
import quru.qa.niffler.model.CurrencyValues;
import quru.qa.niffler.model.SpendJson;
import quru.qa.niffler.page.LoginPage;
import quru.qa.niffler.page.MainPage;


public class SpendingWebTest {

    private static final Config CFG = Config.getInstance();

    @Spend(
            category = "Education",
            description = "Xpath course",
            username = "filkot",
            amount = 10000,
            currency = CurrencyValues.RUB
    )

    @Test
    void categoryDescriptionShouldBeChangedFromTableAction(SpendJson spend) {
        String newDescription = "CSS course";
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .doLogin("filkot", "12345")
                .editSpendingPage(spend.description())
                .setDescription(newDescription)
                .save();

        new MainPage().checkThatTableContainsSpending(newDescription);
    }
}
