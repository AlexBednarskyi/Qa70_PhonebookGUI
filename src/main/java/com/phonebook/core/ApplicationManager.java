package com.phonebook.core;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomePageHelper;
import com.phonebook.fw.UserHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ApplicationManager {


    String browser;
    protected WebDriver driver;
   public static SoftAssert softAssert;

   UserHelper user;
   ContactHelper contact;
   HomePageHelper homePage;

    public ApplicationManager(String browser) {
        this.browser = browser;

    }

    public void init() {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("safari")){
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }else if(browser.equalsIgnoreCase("firefox"))
            WebDriverManager.firefoxdriver().setup();
        driver= new FirefoxDriver();

        driver.get("https://telranedu.web.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        user= new UserHelper(driver);
        contact = new ContactHelper(driver);
        homePage = new HomePageHelper(driver);

        softAssert = new SoftAssert();


    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HomePageHelper getHomePage() {
        return homePage;
    }

    public void stop() {
        driver.quit();
    }

}
