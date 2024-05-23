package org.korolev.dens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPanel extends Page {
    public CategoryPanel(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getName() {
        return Access.getElement(webDriver,
                By.xpath("./html/body/div[1]/div[2]/div[2]/div/div/h3"));
    }
}
