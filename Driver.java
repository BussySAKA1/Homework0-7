import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Driver class.
 *
 * @author David Andrews
 * @version 1.0
 */
public class Driver extends TestRunner {
    /**
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Driver driver = new Driver();
        System.exit(driver.runTests() > 0 ? 1 : 0); // have to stop potential infinite loops
    }

    @Test
    private void testMergeSortRolls() {
        SushiRoll[] input = new SushiRoll[] {new SushiRoll("Tobiko"), new SushiRoll(
                "Avocado"), new SushiRoll("Unagi"), new SushiRoll("Dragon"), new SushiRoll("Maki")};

        SushiRoll[] output = Restaurant.mergeSortRolls(input);

        Assert.assertOrdersEqual(
                new SushiRoll[] {new SushiRoll("Avocado"), new SushiRoll("Dragon"), new SushiRoll(
                        "Maki"), new SushiRoll("Tobiko"), new SushiRoll("Unagi")},
                output, "mergeSortRolls() is not correct");
    }

    @Test
    private void testMergeSortRollsEmpty() {
        SushiRoll[] input = new SushiRoll[] {};

        SushiRoll[] output = Restaurant.mergeSortRolls(input);

        Assert.assertOrdersEqual(new SushiRoll[] {}, output, "mergeSortRolls() is not correct");
    }

    @Test
    private void testMergeSortRollsOne() {
        SushiRoll[] input = new SushiRoll[] {new SushiRoll("Avocado")};

        SushiRoll[] output = Restaurant.mergeSortRolls(input);

        Assert.assertOrdersEqual(new SushiRoll[] {new SushiRoll("Avocado")}, output,
                "mergeSortRolls() is not correct");
    }

    @Test
    private void testMergeOrders() {
        SushiRoll[][] input = new SushiRoll[][] {{}, {new SushiRoll("Avocado"), new SushiRoll(
                "Dragon")}, {new SushiRoll(
                        "Tobiko"), new SushiRoll("Unagi")}, {new SushiRoll("Maki")}, {}};

        SushiRoll[] output = Restaurant.mergeOrders(input);

        Assert.assertOrdersEqual(
                new SushiRoll[] {new SushiRoll("Avocado"), new SushiRoll("Dragon"), new SushiRoll(
                        "Maki"), new SushiRoll("Tobiko"), new SushiRoll("Unagi")},
                output, "mergeOrders() is not correct");
    }

    @Test
    private void testMergeOrdersEmpty() {
        SushiRoll[][] input = new SushiRoll[][] {};

        SushiRoll[] output = Restaurant.mergeOrders(input);

        Assert.assertOrdersEqual(new SushiRoll[] {}, output, "mergeOrders() is not correct");
    }

    @Test
    private void testMergeOrdersOne() {
        SushiRoll[][] input =
                new SushiRoll[][] {{new SushiRoll("Avocado"), new SushiRoll("Dragon")}};

        SushiRoll[] output = Restaurant.mergeOrders(input);

        Assert.assertOrdersEqual(
                new SushiRoll[] {new SushiRoll("Avocado"), new SushiRoll("Dragon")}, output,
                "mergeOrders() is not correct");
    }

    @Test
    private void testPlatesOfColor() {
        SushiRoll[] input = {new SushiRoll("Avocado", Color.BLUE), new SushiRoll("Dragon",
                Color.BLUE), new SushiRoll("Maki", Color.RED), new SushiRoll("Rainbow",
                        Color.GREEN), new SushiRoll("Unagi", Color.RED)};

        SushiRoll[] output = Restaurant.platesOfColor(input, Color.RED);

        Assert.assertOrdersEqual(new SushiRoll[] {new SushiRoll("Maki",
                Color.RED), new SushiRoll("Unagi", Color.RED)}, output,
                "platesOfColor() is not correct");
    }

    @Test
    private void testPlatesOfColorEmpty() {
        SushiRoll[] input = new SushiRoll[] {};

        SushiRoll[] output = Restaurant.platesOfColor(input, Color.RED);

        Assert.assertOrdersEqual(new SushiRoll[] {}, output, "platesOfColor() is not correct");
    }

    @Test
    private void testPlatesOfColorOne() {
        SushiRoll[] input = new SushiRoll[] {new SushiRoll("Avocado", Color.BLUE)};

        SushiRoll[] output = Restaurant.platesOfColor(input, Color.RED);

        Assert.assertOrdersEqual(new SushiRoll[] {}, output, "platesOfColor() is not correct");
    }

    @Test
    private void testTotalPrice() {
        SushiRoll[] input = {new SushiRoll("Avocado", Color.BLUE), new SushiRoll("Dragon",
                Color.BLUE), new SushiRoll("Maki", Color.RED), new SushiRoll("Rainbow",
                        Color.GREEN), new SushiRoll("Unagi", Color.RED)};

        double output = Restaurant.totalPrice(input);

        Assert.assertEqual(15.0, output, "totalPrice() is not correct");
    }

    @Test
    private void testTotalPriceEmpty() {
        SushiRoll[] input = new SushiRoll[] {};

        double output = Restaurant.totalPrice(input);

        Assert.assertEqual(0.0, output, "totalPrice() is not correct");
    }

    @Test
    private void testTotalPriceOne() {
        SushiRoll[] input = new SushiRoll[] {new SushiRoll("Avocado", Color.BLUE)};

        double output = Restaurant.totalPrice(input);

        Assert.assertEqual(3.5, output, "totalPrice() is not correct");
    }

    @Test
    private void testFlip() {
        SushiRoll[] input = {new SushiRoll("Avocado"), new SushiRoll("Dragon"), new SushiRoll(
                "Maki"), new SushiRoll("Tobiko"), new SushiRoll("Unagi")};

        Restaurant.flip(input);

        Assert.assertOrdersEqual(
                new SushiRoll[] {new SushiRoll("Unagi"), new SushiRoll("Tobiko"), new SushiRoll(
                        "Maki"), new SushiRoll("Dragon"), new SushiRoll("Avocado")},
                input, "flip() is not correct");
    }

    @Test
    private void testFlipEmpty() {
        SushiRoll[] input = new SushiRoll[] {};

        Restaurant.flip(input);

        Assert.assertOrdersEqual(new SushiRoll[] {}, input, "flip() is not correct");
    }

    @Test
    private void testFlipOne() {
        SushiRoll[] input = new SushiRoll[] {new SushiRoll("Avocado")};

        Restaurant.flip(input);

        Assert.assertOrdersEqual(new SushiRoll[] {new SushiRoll("Avocado")}, input,
                "flip() is not correct");
    }
}


class TestRunner {
    public int runTests() {
        List<Method> tests = getTests();

        System.out.printf("Running %d test%s\n", tests.size(), tests.size() == 1 ? "" : "s");

        long startTime = System.currentTimeMillis();

        int passed = 0;
        int failed = 0;

        LinkedHashMap<Method, String> failures = new LinkedHashMap<>();

        for (Method test : tests) {
            System.out.printf("Running " + test.getName() + " ... ");
            String stdout = invokeTestMethod(test, 1000);
            if (stdout == null) {
                System.out.println("ok");
                passed++;
                continue;
            }
            System.out.println("FAILED");
            failures.put(test, stdout);
            failed++;
        }

        System.out.println();

        if (!failures.isEmpty()) {
            System.out.println("Failures:");

            System.out.println();

            for (Method test : failures.keySet()) {
                System.out.println(" ---- " + test.getName() + " ---- ");
                System.out.print(failures.get(test));
                System.out.println();
            }

            System.out.println("Failures:");
            for (Method test : failures.keySet()) {
                System.out.println("    " + test.getName());
            }

            System.out.println();
        }

        System.out.printf("Summary: %s. %d passed, %d failed. finished in %.2fs\n",
                failed == 0 ? "ok" : "FAILED", passed, failed,
                (System.currentTimeMillis() - startTime) / 1000.0);

        return failed;
    }

    private List<Method> getTests() {
        return Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .collect(Collectors.toList());
    }

    private String invokeTestMethod(Method method, long timeoutInMillis) {
        method.setAccessible(true);
        final boolean[] failed = {false};
        final boolean[] timeoutOccurred = {false};

        Thread testThread = new Thread(() -> {
            try {
                method.invoke(this);
            } catch (Exception e) {
                e.getCause().printStackTrace();
                failed[0] = true;
            }
        });

        String stdout = captureConsole(() -> {
            testThread.start();
            try {
                testThread.join(timeoutInMillis);
                if (testThread.isAlive()) {
                    testThread.interrupt();
                    timeoutOccurred[0] = true;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                failed[0] = true;
            }
        });

        if (failed[0] || timeoutOccurred[0]) {
            return stdout + (timeoutOccurred[0]
                    ? "(Did not finish within the allowed time and may contain an infinite loop)\n"
                    : "");
        }
        return null;
    }

    // maybe just make captureConsole() and stopCapturingConsole() methods instead? lambdas are
    // annoying with scope and capturing
    protected static String captureConsole(Runnable r) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;

        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));

        r.run();

        System.setOut(originalOut);
        System.setErr(originalErr);

        return out.toString() + err.toString();
    }

    protected static <T> Field getField(Class<T> className, String fieldName) {
        try {
            Field field = className.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            System.err.println("You do not have a " + fieldName + " field in Olivia!");
            System.exit(1);
        }
        return null;
    }

    protected static Object getValue(Object obj, Field field) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            System.err.println("Cannot obtain access to the " + field.getName() + " field!");
            System.exit(1);
        }
        return null;
    }

    protected static void setValue(Object obj, Field field, Object value) {
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            System.err.println("Cannot obtain access to the " + field.getName() + " field!");
            System.exit(1);
        }
    }

    protected static Object getValue(Object obj, String fieldName) {
        return getValue(obj, getField(obj.getClass(), fieldName));
    }

    protected static void setValue(Object obj, String fieldName, Object value) {
        setValue(obj, getField(obj.getClass(), fieldName), value);
    }

    protected static Object getStaticValue(Field field) {
        try {
            return field.get(null);
        } catch (IllegalAccessException e) {
            System.err.println("Cannot obtain access to the " + field.getName() + " field!");
            System.exit(1);
        }
        return null;
    }

    protected static void setStaticValue(Field field, Object value) {
        try {
            field.set(null, value);
        } catch (IllegalAccessException e) {
            System.err.println("Cannot obtain access to the " + field.getName() + " field!");
            System.exit(1);
        }
    }

    protected static Object getStaticValue(Class<?> className, String fieldName) {
        return getStaticValue(getField(className, fieldName));
    }

    protected static void setStaticValue(Class<?> className, String fieldName, Object value) {
        setStaticValue(getField(className, fieldName), value);
    }
}


@Retention(RetentionPolicy.RUNTIME)
@interface Test {
}


class Assert {
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void assertEqual(Object expected, Object actual, String message) {
        if (expected == null && actual != null || !expected.equals(actual)) {
            if (expected instanceof Double && actual instanceof Double
                    || expected instanceof Float && actual instanceof Float) {
                double expectedDouble = (double) expected;
                double actualDouble = (double) actual;
                if (Math.abs(expectedDouble - actualDouble) < 1e-6) {
                    return;
                }
            }
            throw new AssertionError(
                    String.format("%s\nExpected '%s' but got '%s'", message, expected, actual));
        }
    }

    public static void assertInstanceOf(Object obj, Class<?> className, String message) {
        if (obj == null || !className.isInstance(obj)) {
            throw new AssertionError(String.format("%s\nExpected instance of '%s' but got '%s'",
                    message, className.getName(), obj == null ? "null" : obj.getClass().getName()));
        }
    }

    public static void assertSushiRollsEqual(SushiRoll expected, SushiRoll actual, String message) {
        if (expected == null && actual != null || expected.compareTo(actual) != 0
                || expected.getColor() != actual.getColor()) {
            throw new AssertionError(
                    String.format("%s\nExpected '%s' but got '%s'", message, expected, actual));
        }
    }

    public static void assertOrdersEqual(SushiRoll[] expected, SushiRoll[] actual, String message) {
        if (expected == null && actual != null || expected.length != actual.length) {
            throw new AssertionError(String.format("%s\nExpected '%s' but got '%s'", message,
                    Arrays.asList(expected), Arrays.asList(actual)));
        }

        for (int i = 0; i < expected.length; i++) {
            try {
                assertSushiRollsEqual(expected[i], actual[i], "");
            } catch (AssertionError e) {
                throw new AssertionError(String.format("%s\nExpected '%s' but got '%s'", message,
                        Arrays.asList(expected), Arrays.asList(actual)));
            }
        }
    }
}