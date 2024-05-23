package org.korolev.dens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfileModal extends Page {
    public ProfileModal(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getMessagesButton() {
        return Access.getElement(webDriver, By.xpath("./html/body/div/div[1]/ul/li[3]"));
    }

    public WebElement getInformationMessagesText() {
        return webDriver.findElement
                (By.xpath("./html/body/div/div[2]/table/tbody/tr/td[2]/span[2]/strong"));
    }

    public WebElement getAwardsButton() {
        return Access.getElement(webDriver, By.xpath("./html/body/div/div[1]/ul/li[4]"));
    }

    public WebElement getYourAwardsText() {
        return webDriver.findElement
                (By.xpath("./html/body/div/div[3]/table/tbody/tr[2]/td/h2"));
    }

    public WebElement getStatisticsButton() {
        return Access.getElement(webDriver, By.xpath("./html/body/div/div[1]/ul/li[5]"));
    }

    public WebElement getFeedBackText() {
        return webDriver.findElement
                (By.xpath("./html/body/div/div[2]/table/tbody/tr/td[1]"));
    }

    public WebElement getCorrectionsAtAllText() {
        return webDriver.findElement
                (By.xpath("./html/body/div/div[2]/table/thead/tr/th[1]"));
    }

    public WebElement getInstrumentsButton() {
        return Access.getElement(webDriver, By.xpath("./html/body/div/div[1]/ul/li[6]"));
    }

    public WebElement getForAllUsersInstrumentsText() {
        return webDriver.findElement
                (By.xpath("./html/body/div/div[2]/h3"));
    }

    public WebElement getLevelPlus1InstrumentsText() {
        return webDriver.findElement
                (By.xpath("./html/body/div/div[4]/h3"));
    }

    public WebElement getUserNameText() {
        return webDriver.findElement(By.xpath("./html/body/div/div[2]/div[1]/div[3]/h1"));
    }
    public WebElement getProfileTitle() {
        return webDriver.findElement(By.xpath("./html/body/div/div[2]/div[1]/div[3]/span"));
    }
    public WebElement getLevelTitle() {
        return webDriver.findElement(By.xpath("./html/body/div/div[2]/div[1]/table/tbody/tr[1]/th"));
    }
    public WebElement getLevel() {
        return webDriver.findElement(By.xpath("./html/body/div/div[2]/div[1]/table/tbody/tr[1]/td"));
    }
    public WebElement getRatingTitle() {
        return webDriver.findElement(By.xpath("./html/body/div/div[2]/div[1]/table/tbody/tr[3]/th"));
    }
    public WebElement getRating() {
        return webDriver.findElement(By.xpath("./html/body/div/div[2]/div[1]/table/tbody/tr[3]/td/a"));
    }

}
