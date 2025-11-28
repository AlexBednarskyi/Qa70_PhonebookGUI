package com.phonebook.core;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomePageHelper;
import com.phonebook.fw.UserHelper;
import com.phonebook.fw.ItemHelper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class ApplicationManager {

    private WebDriver driver;

    private UserHelper user;
    private ContactHelper contact;
    private ItemHelper item;
    private HomePageHelper home;

    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.setCapability("unhandledPromptBehavior", "ignore");

            driver = new ChromeDriver(options);
        }

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            options.addPreference("media.volume_scale", "0.0");
            options.addPreference("network.http.prompt-temp-redirect", false);
            options.setCapability("unhandledPromptBehavior", "ignore");

            driver = new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://telranedu.web.app/");

        // ИНИЦИАЛИЗАЦИЯ ХЕЛПЕРОВ
        user = new UserHelper(driver);
        contact = new ContactHelper(driver);
        item = new ItemHelper(driver);
        home = new HomePageHelper(driver);
    }

    public void stop() {
        driver.quit();
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public ItemHelper getItem() {
        return item;
    }

    public HomePageHelper getHomePage() {
        return home;
    }
}
