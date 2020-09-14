package ru.netology.web;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


class RegistrationTest {

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }


    @Test
    void shouldRegisterByCardNumber() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Красноярск");
        LocalDate dateDay = LocalDate.now().plusDays(8);
        String dateToEnter = dateDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateToEnter);
        $("[data-test-id=name] input").setValue("Киселев Тимур");
        $("[data-test-id=phone] input").setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 11000).shouldHave(text(dateToEnter));
    }
}
