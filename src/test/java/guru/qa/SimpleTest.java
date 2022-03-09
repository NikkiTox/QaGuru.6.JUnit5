package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс с простыми тестами")
public class SimpleTest {
    @Test
    @DisplayName("Ожидаемо зелёный тест")
    void simpleGreenTest() {
        //System.out.println("Hello, world!");
    assertTrue (3 > 2);
    }
    @Test
    @DisplayName("Ожидаемо красный тест")
    void simpleRedTest() {
        assertTrue(3 < 2);
    }
    @Test
    @DisplayName("Ожидаемо сломаный тест")
    void simpleBrokenTest(){
        throw new IllegalStateException("Sorry I am broken:(");
    }


}
