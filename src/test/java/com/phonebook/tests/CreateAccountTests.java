package com.phonebook.tests;
import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
public class CreateAccountTests extends TestBase{


    @Test(enabled = false)
    public void newUserRegisterPositiveTest(){
        //int i =(int)((System.currentTimeMillis()/1000)%3600);
        //click on Login Link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        //assert SigOut button present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void newUserRegisterNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}