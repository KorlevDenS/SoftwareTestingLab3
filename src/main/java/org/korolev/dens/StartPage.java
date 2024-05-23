package org.korolev.dens;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage extends Page {

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getActivityButton() {
        return webDriver.findElement(
                By.xpath("./html/body/div[1]/div[1]/div[3]/div[1]/div/span"));
    }

    public WebElement getCategoriesDropdown() {
        return webDriver.findElement(
                By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[2]"));
    }

    public WebElement getCategoryDropdownVariant(int index) {
        return webDriver.findElement(
                By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[2]/ul/li[" + (index + 2) + "]")
        );
    }

    public WebElement getCategoryLookUpText() {
        return Access.getElement(webDriver, By.xpath(
                "./html/body/div[1]/div[1]/div[3]/div[1]/div/div[2]/span"));
    }

    public WebElement getCategoryContentName() {
        return Access.getElement(webDriver, By.xpath(
                "./html/body/div[1]/div[1]/div[3]/div[1]/div/div[2]/a"));
    }

    public WebElement getCategoryContent() {
        return Access.getElement(webDriver,
                By.xpath("./html/body/div[1]/div[1]/div[3]/div[1]/div"));
    }

    public WebElement getZoomButton() {
        return webDriver.findElement(By.xpath(
                "./html/body/div[1]/div[1]/div[3]/div[3]/div[4]"));
    }

    public WebElement getSaveButton() {
        return Access.getElement(webDriver, By.xpath(
                "./html/body/div[1]/div[1]/div[3]/div[1]/div/div[2]/input[1]"
        ));
    }

    public WebElement getAddButton() {
        return webDriver.findElement(By.xpath(
                "./html/body/div[1]/div[1]/div[3]/div[10]"));
    }

    public void logout() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(Access.getElement(webDriver,
                By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[3]/a"))).perform();
        actions.moveToElement(Access.getElement(webDriver,
                By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[3]/ul/li[14]")))
                .click().perform();
    }

    @SneakyThrows
    public void login(String username, String password) {
        WebElement loginDropdown = Access.getElement(
                webDriver, By.xpath(".//*[@class='n-0-2 item-2 dropdown']")
        );
        loginDropdown.click();
        webDriver.switchTo().frame(Access.getElement(
                webDriver, By.xpath(".//iframe[@class='modal-body modal-iframe']")
        ));
        WebElement loginField = Access.getElement(
                webDriver, By.xpath(".//*[@id=\"user-login\"]/fieldset/div[2]/div/input")
        );
        WebElement passwordField = Access.getElement(
                webDriver, By.xpath(".//*[@id=\"user-login\"]/fieldset/div[3]/div/input")
        );
        WebElement inputButton = Access.getElement(
                webDriver, By.xpath(".//*[@id=\"user-login\"]/div[2]/input")
        );
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
        inputButton.click();
        webDriver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        try {
            wait.until((ExpectedCondition<Boolean>) _ -> {
                WebElement loginButtonText = Access.getElement(
                        webDriver, By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[3]/a")
                );
                return loginButtonText.getText().equals(Access.CORRECT_LOGIN);
            });
        } catch (TimeoutException e) {
            throw new Exception("Not logged in for 10 seconds");
        }
        WebElement closeButton = Access.getElement(
                webDriver, By.xpath("./html/body/div[1]/div[1]/div[3]/div[16]/div/span[1]")
        );
        closeButton.click();
    }

}
