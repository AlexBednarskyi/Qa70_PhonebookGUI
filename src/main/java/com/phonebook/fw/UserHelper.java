package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {


    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    public void fillLoginRegisterForm(User user) {
        type(By.name("email"), user.getEmail());
        //driver.findElement(By.name("email")).sendKeys("manuel67"+i+"@gmail.com");
        //enter password
        type(By.name("password"), user.getPassword());
    }

    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    public void clickOnRegistrationButton() {
        click(By.name("registration"));
    }

    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[.='Sign Out']"));
    }

    public boolean isErrorMessagePresent() {
        return isElementPresent(By.cssSelector(".login_login__3EHKB>div"));
    }
}
