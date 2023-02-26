package quru.qa.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;

public class RegistrationResultsModal {

    public void verifyModalAppears() {
        Selenide.$(".modal-dialog").should(Condition.appear);
        Selenide.$("#example-modal-sizes-title-lg")
            .shouldHave(Condition.text("Thanks for submitting the form"));
    }

    public void verifyResult(String key, String value) {
        Selenide.$(".table-responsive").$(Selectors.byText(key)).parent()
            .shouldHave(Condition.text(value));
    }
}
