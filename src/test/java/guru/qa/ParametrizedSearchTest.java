package guru.qa;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ParametrizedSearchTest {
    @BeforeAll
   public static void preconditionBrowser(){
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void precondition() {

        Selenide.open("https://demoqa.com/text-box");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }


    @ValueSource(strings = {"Ivanov", "Petrov"})
    @ParameterizedTest(name = "Проверка отображение правильности регистрации DemoQA с помощью Value Source \"{0}\"")
    void commonSearchTest(String testData) {
        $("#userName").setValue(testData);
        $("#userEmail").setValue(testData + "@mail.ru");
        $("#currentAddress").setValue("Moscow");
        $("#permanentAddress").setValue("Kremlin");
        $("#submit").click();
        $("#output").shouldHave(text(testData));
    }

    //@Disabled
    @CsvSource (value = {
            "Ivanov* Moscow* Kremlin",
            "Petrov* St.Petersburg* Kronshtadt"
    },
            delimiter = '*'
    )
    @ParameterizedTest(name = "Проверка отображение правильности регистрации DemoQA с Parameterized Test \"{0}\"")
    void complexSearchTest(String testData, String expectedText) {
        $("#userName").setValue(testData);
        $("#userEmail").setValue(testData + "@mail.ru");
        $("#currentAddress").setValue(expectedText);
        $("#permanentAddress").setValue(expectedText);
        $("#submit").click();
        $("#output").shouldHave(text(testData));
    }

    static Stream<Arguments>mixedArgumentsTestDataProvider(){
        return Stream.of(
                Arguments.of("Ivanov", List.of("Moscow","Kremlin","Red Square"),
                Arguments.of("Petrov", List.of("St.Petersburg","Kronshtadt","Kotlin Island")
                )));

    }
    //@Disabled
    @MethodSource(value ="mixedArgumentsTestDataProvider")
    @ParameterizedTest
    void mixedArgumentsTest(String firstArg, List<String> secondArg){
        $("#userName").setValue(firstArg);
        $("#userEmail").setValue(firstArg + "@mail.ru");
        $("#currentAddress").setValue(String.valueOf(secondArg));
        $("#permanentAddress").setValue(String.valueOf(secondArg));
        $("#submit").click();
        $("#output").shouldHave(text(firstArg));


    }
}