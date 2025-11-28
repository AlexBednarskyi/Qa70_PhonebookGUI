package com.phonebook.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

public class BaseHelper {

    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        if (text != null) {
            element.sendKeys(text);
        }
    }

    protected boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    // ждём алерт до 5 секунд
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void acceptAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // ignore
        }
    }

    public String takeScreenshot() {
        // если алерт открыт — пробуем закрыть
        try {
            acceptAlertIfPresent();
        } catch (Exception ignored) {
        }

        File tmp;
        try {
            tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } catch (UnhandledAlertException e) {
            logger.warn("Cannot take screenshot because of open alert: {}", e.getMessage());
            return null;
        }

        String path = "logs/screenshots/screen-" + UUID.randomUUID() + ".png";
        File dest = new File(path);
        dest.getParentFile().mkdirs();

        try {
            FileUtils.copyFile(tmp, dest);
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
            return null;
        }

        return dest.getAbsolutePath();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}
