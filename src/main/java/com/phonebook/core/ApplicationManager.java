package com.phonebook.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {


   protected WebDriver driver;
   UserHelper user;
   ContactHelper contact;
   HomePageHelper homePage;


    public void init() {
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get("https://telranedu.web.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        user= new UserHelper(driver);
        contact = new ContactHelper(driver);
        homePage = new HomePageHelper(driver);


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
