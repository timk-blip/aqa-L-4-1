package ru.netology.web;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {


    @BeforeAll
    void setUp() {
        System.setProperty("selenide.browser","Chrome");
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldRegisterByCardNumber() {
        open("http://localhost:9999");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        int nextDay = date.getDayOfMonth() + 7;
        $$("[placeholder=\"Город\"]").last().setValue("Нижний Новгород");
        $$("[placeholder=\"Дата встречи\"]").last().setValue(String.valueOf(date));
        $$("[class='calendar__row'] > [class='calendar__day']").find(exactText(String.valueOf(nextDay))).click();
        $$("[name='name']").last().setValue("Фамилия");
        $$("[name='phone']").last().setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).waitUntil(visible, 11000);
    }
}

