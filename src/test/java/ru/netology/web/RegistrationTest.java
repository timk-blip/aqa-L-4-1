package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    void shouldRegisterByCardNumber() {

        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        int nextDay = date.getDayOfMonth() + 7;
        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern(String.valueOf(nextDay / month / year));
        LocalDate localDate = LocalDate.now();
        String dateString = FOMATTER.format(localDate);

        open("http://localhost:9999");
        $$("[placeholder=\"Город\"]").last().setValue("Нижний Новгород");

        $$("[placeholder=\"Дата встречи\"]").last().setValue(String.valueOf(nextDay / month / year));
        $$("[class='calendar__row'] > [class='calendar__day']").find(exactText(String.valueOf(nextDay))).click();
        $$("[name='name']").last().setValue("Фамилия");
        $$("[name='phone']").last().setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).waitUntil(visible, 11000);
    }
}

