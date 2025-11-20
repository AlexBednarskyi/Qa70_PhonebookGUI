package com.phonebook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"),"manuel67@gmail.com");
        //driver.findElement(By.name("email")).sendKeys("manuel67"+i+"@gmail.com");
        type(By.name("password"), "Pp1234567!");
        click(By.name("login"));
    }
    @Test
    public void addContactPositiveTest(){
        //click on Add link
        click(By.cssSelector("[href='/add']"));
        //enter name
        type(By.xpath("//input[1]"),"Alex");
        //enter lastname
        type(By.xpath("//input[2]"),"Stern");
        //enter phone
        type(By.xpath("//input[3]"),"1234567890");
        //enter email
        type(By.xpath("//input[4]"),"test@gm.com");
        //enter address
        type(By.xpath("//input[5]"),"Berlin");
        //enter description
        type(By.xpath("//input[6]"),"QA");
        //click on Save button
        click(By.cssSelector(".add_form__2rsm2 button"));
        //assert by name(text)
        Assert.assertTrue(isContactCreatedByText("Alex"));
    }
    public boolean isContactCreatedByText(String text) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element:contacts) {
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }
}
