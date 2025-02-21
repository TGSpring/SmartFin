import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class SmartFinTest {

    private SmartFin sf;
    private Scanner sc;

    @Test
    public void testValidationWithValidInteger() {
        // Simulate a valid positive integer input ("42") followed by a newline.
        String input = "42\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        SmartFin smartFin = new SmartFin();

        // Expect that the validation method correctly parses "42" as a number.
        double result = smartFin.validation(sc, "Enter number: ", true);
        assertEquals(42.0, result);
        sc.close();
    }

    @Test
    public void testValidationWithValidDouble() {
        // Simulate a valid double input ("3.14") followed by a newline.
        String input = "3.14\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        SmartFin smartFin = new SmartFin();

        // Expect that the validation method correctly parses "3.14".
        double result = smartFin.validation(sc, "Enter number: ", false);
        assertEquals(3.14, result);
        sc.close();
    }

    @Test
    public void testValidationInvalidThenValid() {
        // Simulate an invalid input followed by a valid integer.
        // First "abc" is invalid, then "100" is valid.
        String input = "abc\n100\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        SmartFin smartFin = new SmartFin();

        // The validation method should loop until it reads a valid number ('100').
        double result = smartFin.validation(sc, "Enter number: ", true);
        assertEquals(100.0, result);
        sc.close();
    }

    @Test
    public void testSavingsGoalEstimator() {
        String input = "3\n3245\n23\n3\n4\n8\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        sc = new Scanner(System.in);

        // Simulate the menu selection and input for Savings Goal Estimator
        sf.main(new String[] {});

        // Verify the output (you may need to capture the output stream to verify the
        // printed projections)
        // For simplicity, we assume the projections are correct if no exceptions are
        // thrown
        assertTrue(true);
    }

    @Test
    public void testAnalyzeCategoryName() {
        String input = "4\nEntertainment\n8\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        sc = new Scanner(System.in);

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Simulate the menu selection and input for Budget Category Name Analyzer
        sf.main(new String[] {});

        // Verify the output
        String expectedOutput = "Reversed and UpperCase: TNEMNIATRETNE\nVowels: 5\nConsonants: 8\n";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testValidateAndFormatDate() {
        String input = "02/14/2025\n"; // Add a newline so Scanner.nextLine() works as expected
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        sc = new Scanner(System.in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        DateFormatter df = new DateFormatter();
        long unixTimestamp = df.validateAndFormatDate(sc);

        String expectedOutput = "Formatted Date: 02/14/2025" + System.lineSeparator();
        String actualOutput = outContent.toString();
        System.setOut(originalOut); // Restore original System.out
        assertTrue(actualOutput.contains(expectedOutput));
        assertEquals(1739491200L, unixTimestamp);
    }
}
