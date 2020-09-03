package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        open("http://localhost:9999");
        $$(".tab-item").find(exactText("По номеру счёта")).click();
        $("[name='number']").setValue("4055 0100 0123 4613 8564");
        $("[name='phone']").setValue("+792000000000");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Успешная авторизация")).waitUntil(visible, 5000);
        $(byText("Личный кабинет")).waitUntil(visible, 5000);
    }

    @Test
    void shouldRegisterByAccountNumberVisibilityChange() {
        open("http://localhost:9999");
        $$(".tab-item").find(exactText("По номеру счёта")).click();
        $$("[name='number']").last().setValue("4055 0100 0123 4613 8564");
        $$("[name='phone']").last().setValue("+792000000000");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Успешная авторизация")).waitUntil(visible, 5000);
        $(byText("Личный кабинет")).waitUntil(visible, 5000);
    }

    @Test
    void shouldRegisterByCardNumber() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        date = date.plusYears(0);
        date = date.plusMonths(0);
        date = date.plusDays(5);

        open("http://localhost:9999");
        $$("[placeholder=\"Город\"]").last().setValue("Нижний Новгород");
     /*  $$("[class='icon icon_size_m icon_name_calendar icon_theme_alfa-on-white']").last().click();


*/
        $$("#tbody").find(Condition.attribute("[class=\"calendar__row\"]")).find(String.valueOf(Condition.attribute("[class=\"calendar__day calendar__day_type_off\"]"))).findAll("class=\"calendar__day calendar__day_type_off calendar__day_state_today\"");
      // $$("[placeholder=\"Дата встречи\"]").last().setValue("11.11.2021");
        $$("[name='name']").last().setValue("Фамилия");
        $$("[name='phone']").last().setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).waitUntil(visible, 11000);
    }
}

