package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest(name = "add({0},{1}) = {2}")
    @CsvSource({
            "1, 2, 3",
            "-5, 10, 5",
            "0, 0, 0"
    })
    void add_shouldReturnSum(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest(name = "subtract({0},{1}) = {2}")
    @CsvSource({
            "10, 3, 7",
            "3, 10, -7",
            "0, 0, 0"
    })
    void subtract_shouldReturnDifference(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    @ParameterizedTest(name = "multiply({0},{1}) = {2}")
    @CsvSource({
            "2, 3, 6",
            "-4, 5, -20",
            "0, 99, 0"
    })
    void multiply_shouldReturnProduct(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }

    @ParameterizedTest(name = "divide({0},{1}) = {2}")
    @CsvSource({
            "8, 2, 4",
            "9, 3, 3",
            "-10, 2, -5"
    })
    void divide_shouldReturnQuotient(int a, int b, int expected) {
        assertEquals(expected, calculator.divide(a, b));
    }

    @Test
    @DisplayName("divide should throw ArithmeticException when divisor is zero")
    void divide_byZero_shouldThrow() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Test
    @DisplayName("Algebraic property: (a + b) - b = a (for a small set)")
    void addThenSubtract_shouldReturnOriginal() {
        int[] values = {-10, -1, 0, 1, 10};
        for (int a : values) {
            for (int b : values) {
                int result = calculator.subtract(calculator.add(a, b), b);
                assertEquals(a, result, "Failed for a=" + a + ", b=" + b);
            }
        }
    }
}
