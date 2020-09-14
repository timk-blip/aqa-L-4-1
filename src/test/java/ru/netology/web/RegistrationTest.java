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
    void shouldComplexElements() {

        open("http://localhost:9999");
        $$("[placeholder=\"Город\"]").last().setValue("Ка");
        $(withText("Красноярск")).click();
        //////////////////////////////////////////////////////////////////////
        LocalDate date = LocalDate.now();
        String currentDay = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        int month = date.getMonthValue();
        int year = date.getYear();
        String nextDate = "15.04.2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateTime = LocalDate.parse(nextDate, formatter);
        int nextDay = dateTime.getDayOfMonth();
        int nextMonth = dateTime.getMonthValue();
        int nextYear = dateTime.getYear();
        int resultYear = 0;
        if (year <= nextYear) {
            resultYear = nextYear - year;
        }
        int resultMonth = 0;
        if (nextMonth >= month) {
            resultMonth = nextMonth - month;
        } else {
            resultYear--;
            resultMonth = nextMonth - month + 12;
        }
        $("[class='icon icon_size_m icon_name_calendar icon_theme_alfa-on-white']").click();
        for (int i = 0; i < resultYear; i++) {
            $("[class='calendar__arrow calendar__arrow_direction_right calendar__arrow_double']").click();
        }
        for (int i = 0; i < resultMonth; i++) {
            $("[class='calendar__arrow calendar__arrow_direction_right']").click();
        }
        $$("[class='calendar__row'] > [role='gridcell']").find(exactText(String.valueOf(nextDay))).click();
        //////////////////////////////////////////////////////////////////////
        $("[data-test-id=name] input").setValue("Киселев Тимур");
        $("[data-test-id=phone] input").setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 11000).shouldHave(text(nextDate));
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
