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

   /* @Test
    void shouldComplexElements() throws ParseException {
/*
        open("http://localhost:9999");
        $$("[placeholder=\"Город\"]").last().setValue("Ка");
        $(withText("Красноярск")).click();

        //////////////////////////////////////////////////////////////////////
        LocalDateTime date = LocalDateTime.now();
        String currentDay = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        ZonedDateTime localNow = Instant.now().atZone(ZoneOffset.systemDefault());
        ZonedDateTime localAtCristsBirth = Instant.parse("0001-01-01T00:00:00.00Z").atZone(ZoneOffset.systemDefault());
        Duration timePassed = Duration.between(localAtCristsBirth, localNow);
        $("[data-test-id=date] input").data(String.valueOf(timePassed));
        timePassed.getSeconds();

        String str = "15.04.2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Duration timeDate = Duration.between(localAtCristsBirth, localNow);
        dateTime(String.valueOf(timeDate));


        //////////////////////////////////////////////////////////////////////
        $("[data-test-id=name] input").setValue(String.valueOf(timePassed.getSeconds()));
        $("[data-test-id=phone] input").setValue(String.valueOf(dateTime.getSecond()));
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).waitUntil(visible, 11000);
        $(withText(currentDay)).waitUntil(visible, 11000);
    }
*/
    @Test
    void shouldRegisterByCardNumber() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Красноярск");
        LocalDate dateDay = LocalDate.now().plusDays(8);
        String dateToEnter = dateDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(String.valueOf(dateDay));
        $$("[class='calendar__row'] > [class='calendar__day']").find(exactText(String.valueOf(dateDay.getDayOfMonth()))).click();
        $("[data-test-id=name] input").setValue("Фамилия и Имя");
        $("[data-test-id=phone] input").setValue("+79169682127");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 11000);
        $(withText(dateToEnter)).waitUntil(visible, 11000);
    }
}

