package quru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");

    public EditSpendingPage editSpendingPage(String spendingDescription) {
        tableRows.find(text(spendingDescription)).$("td", 5).click();
        return new EditSpendingPage();
    }

    public void checkThatTableContainsSpending(String spendingDescription) {
        tableRows.find(text(spendingDescription)).shouldBe(visible);
    }
}
