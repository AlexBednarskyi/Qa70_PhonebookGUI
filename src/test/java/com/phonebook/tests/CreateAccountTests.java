package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.data.MyDataProviders;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(enabled = false)
    public void newUserRegisterPositiveTest() {

        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm(
                new User()
                        .setEmail("alex" + i + "@gmail.com")
                        .setPassword("A12345$")
        );

        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    
    @Test(
            enabled = false,
            dataProvider = "validUserFromCsv",
            dataProviderClass = MyDataProviders.class
    )
    public void newUserRegisterFromCsvTest(User user) {

        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm(user);

        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(
                app.getUser().isSignOutButtonPresent(),
                "Кнопка Sign Out не найдена после регистрации (CSV)"
        );
    }

    @Test
    public void newUserRegisterNegativeTest() {
        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm(
                new User()
                        .setEmail("test@gmail.com")
                        .setPassword("a12345")    
        );

        app.getUser().clickOnRegistrationButton();

        
        app.getUser().pause(2000);

        
        Assert.assertTrue(app.getUser().isAlertPresent());

        
        app.getUser().acceptAlertIfPresent();
    }
}
