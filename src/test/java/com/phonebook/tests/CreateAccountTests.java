package com.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{


    @Test
    public void newUserRegisterPositiveTest(){

        //int i =(int)((System.currentTimeMillis()/1000)%3600);

        //click on Login Link
        click(By.cssSelector("[href='/login']"));
        //enter email
        type(By.name("email"),"manuel67@gmail.com");
        //driver.findElement(By.name("email")).sendKeys("manuel67"+i+"@gmail.com");
        //enter password
        type(By.name("password"), "Pp1234567!");
        //click on Registration button
        click(By.name("registration"));
        //assert SigOut button present
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
    }



    @Test
    public void newUserRegisterNegativeTest(){
        //click on Login Link
        click(By.cssSelector("[href='/login']"));
        //enter email
        type(By.name("email"),"manuel67@gmail.com");
        //enter password
        type(By.name("password"), "Pp1234567!");
        //click on Registration button
        click(By.name("registration"));
        //assert alert present
        Assert.assertTrue(isAlertPresent());
    }

}