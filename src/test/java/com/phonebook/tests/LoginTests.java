package com.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
        @Test
        public void loginPositiveTest(){
    click(By.cssSelector("[href='/login']"));
    //enter email
    type(By.name("email"),"manuel67@gmail.com");
    //driver.findElement(By.name("email")).sendKeys("manuel67"+i+"@gmail.com");
    //enter password
    type(By.name("password"), "Pp1234567!");
    //click on Login Button
            click(By.name("login"));
            //assert SigOut button present
            Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));

    }
}