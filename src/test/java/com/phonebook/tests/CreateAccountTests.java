package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        // Если пользователь уже залогинен — выходим
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

    @Test
    public void newUserRegisterNegativeTest() {
        app.getUser().clickOnLoginLink();

        // Либо существующий email, либо заведомо неверный формат — не важно,
        // главное, что появится alert с ошибкой
        app.getUser().fillLoginRegisterForm(
                new User()
                        .setEmail("test@gmail.com")
                        .setPassword("a12345")    // специально неправильный пароль
        );

        app.getUser().clickOnRegistrationButton();

        // ждём появления alert
        app.getUser().pause(2000);

        // проверяем, что alert появился
        Assert.assertTrue(app.getUser().isAlertPresent());

        // ОБЯЗАТЕЛЬНО закрываем alert, чтобы он не мешал другим тестам
        app.getUser().acceptAlertIfPresent();
    }
}
