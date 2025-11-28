package com.phonebook.core;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    protected Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @BeforeMethod
    public void startTest(Method method) {
        logger.info("=== START TEST: {} ===", method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        if (result.isSuccess()) {
            logger.info("PASSED: {}", testName);
        } else {
            String screenshotPath = app.getContact().takeScreenshot();
            logger.error(
                    "FAILED: {} | Screenshot: {}",
                    testName,
                    screenshotPath,
                    result.getThrowable()
            );
        }

        logger.info("=== END TEST: {} ===", testName);
        logger.info("--------------------------------------------");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}
