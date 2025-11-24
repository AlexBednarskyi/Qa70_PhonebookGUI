package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

        @Test
        public void loginPositiveTest(){
            app.getUser().clickOnLoginLink();
            app.getUser().fillLoginRegisterForm(new User()
                    .setEmail(UserData.email)
                    .setPassword(UserData.password));
            app.getUser().clickOnLoginButton();
            Assert.assertTrue(app.getUser().isSignOutButtonPresent());

    }
    @Test
        public void loginNegativeWithoutEmailTest(){
            app.getUser().clickOnLoginLink();
            app.getUser().fillLoginRegisterForm(new User()
                    .setPassword(UserData.password));
            app.getUser().clickOnLoginButton();
            Assert.assertTrue(app.getUser().isAlertPresent());

    }

}