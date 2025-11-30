package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    public void clickOnLoginButton() {
        click(By.xpath("//button[.='Login']"));
    }

    public void clickOnRegistrationButton() {
        click(By.xpath("//button[.='Registration']"));
    }

    
    public void clickOnSignOutButton() {
        click(By.xpath("//button[.='Sign Out']"));
    }

    
    public void logout() {
        if (isSignOutButtonPresent()) {
            clickOnSignOutButton();
        }
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    public void fillLoginRegisterForm(User user) {
        if (user.getEmail() != null) {
            type(By.name("email"), user.getEmail());
        }
        if (user.getPassword() != null) {
            type(By.name("password"), user.getPassword());
        }
    }
}
