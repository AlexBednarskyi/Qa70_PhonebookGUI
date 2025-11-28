package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemHelper extends BaseHelper {

    public ItemHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnSecondAddToCartButton() {
        click(By.xpath("(//button[contains(.,'Add to cart')])[2]"));
    }

    public boolean isItemPresentInCart(String itemName) {
        return isElementPresent(By.xpath("//*[contains(text(),'" + itemName + "')]"));
    }
}
