package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LearningSoulsGameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testMain() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Method m = c.getMethod("main", String[].class);

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a static method called main");
        }
    }
}