package ru.netology;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.exactText;


public class CardDeliveryTest {
    @Test
    void shouldSumbitRequest() {
        open("http://localhost:9999/");
        $("[placeholder]").setValue("Уфа");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String dt = sdf.format(date);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 2);
        dt = sdf.format(c.getTime());
        $("#root > div > form > fieldset > div:nth-child(2) > span > span > span > span > span.input__box > input").setValue(dt);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79123456789");
        $(".checkbox__box").click();
        $("[role=button][type=button].button").click();
        $(withText("Успешно")).waitUntil(visible,5000);
    }
}