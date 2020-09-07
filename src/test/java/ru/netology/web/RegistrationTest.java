package ru.netology.web;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
    void shouldComplexElements() {

        open("http://localhost:9999");
        LocalDate date = LocalDate.now();
        int nextDay = date.getDayOfMonth() + 7;
        $$("[placeholder=\"Город\"]").last().setValue("Ка");
        $(withText("Красноярск")).click();
        $$("[placeholder=\"Дата встречи\"]").last().setValue(String.valueOf(date));
        $$("[class='calendar__row'] > [class='calendar__day']").find(exactText(String.valueOf(nextDay))).click();
        $$("[name='name']").last().setValue("Фамилия");
        $$("[name='phone']").last().setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).waitUntil(visible, 11000);
    }

    @Test
    void shouldRegisterByCardNumber() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Красноярск");
        LocalDate dateDay = LocalDate.now().plusDays(8);
        String dateToEnter = dateDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $$("[placeholder=\"Дата встречи\"]").last().setValue(String.valueOf(dateDay));
        $$("[class='calendar__row'] > [class='calendar__day']").find(exactText(String.valueOf(dateDay.getDayOfMonth()))).click();
        $("[data-test-id=name] input").setValue("Фамилия и Имя");
        $("[data-test-id=phone] input").setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 11000);
        $(withText(dateToEnter)).waitUntil(visible, 11000);
    }
}

