package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import base.BaseTest;

public class Hooks {

    @Before
    public void setUp() {
        BaseTest.initDriver();
    }

    @After
    public void tearDown() {
        BaseTest.quitDriver();
    }
}