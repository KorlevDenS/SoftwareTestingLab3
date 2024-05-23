package org.korolev.dens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObjectAddForm extends Page {
    public ObjectAddForm(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLangText() {
        return Access.getElement(webDriver,
                By.xpath("./html/body/div[1]/div[2]/div[3]/div/div/form/div[1]/h5"));
    }

    public WebElement getDescriptionText() {
        return Access.getElement(webDriver,
                By.xpath("./html/body/div[1]/div[2]/div[3]/div/div/form/div[3]/h5"));
    }

    public WebElement getAddressText() {
        return Access.getElement(webDriver, By.xpath(
                "./html/body/div[1]/div[2]/div[3]/div/div/form/div[4]/div[1]/div[1]/span/span[2]"));
    }

    public WebElement getCategoriesText() {
        return Access.getElement(webDriver, By.xpath(
                "./html/body/div[1]/div[2]/div[3]/div/div/form/div[4]/div[2]/div[1]/span/span[2]"));
    }

    public WebElement getBuildingText() {
        return Access.getElement(webDriver, By.xpath(
                "./html/body/div[1]/div[2]/div[3]/div/div/form/div[4]/div[3]/div[1]/span/span[2]"));
    }

    public WebElement getWikiLinkText() {
        return Access.getElement(webDriver, By.xpath(
                "./html/body/div[1]/div[2]/div[3]/div/div/form/div[4]/div[4]/div[1]/span/span[2]"));
    }
}
