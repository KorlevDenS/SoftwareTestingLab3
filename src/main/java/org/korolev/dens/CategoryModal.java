package org.korolev.dens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryModal extends Page {


    public CategoryModal(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getDescription() {
        return Access.getElement(webDriver,
                By.xpath("./html/body/div/div[1]/div[3]/div/div[3]/div/p[2]"));
    }

}
