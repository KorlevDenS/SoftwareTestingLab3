package org.korolev.dens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddObjectTest {

    private List<WebDriver> drivers;

    @BeforeEach
    void setDriversAndLogin() {
        this.drivers = Access.getDrivers();
        this.drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            webDriver.get(Access.BASE_URL);
            startPage.login(Access.CORRECT_LOGIN, Access.CORRECT_PASSWORD);
        });
    }

    @Test
    void tryCreateObject() {
        this.drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(startPage.getZoomButton()).click().perform();
            Access.giveSomeTime(4);
            actions.moveToElement(startPage.getAddButton()).click().perform();
            Access.giveSomeTime(4);
            WebElement saveButton = startPage.getSaveButton();
            assertFalse(saveButton.isEnabled());
            actions.moveByOffset(300, 250).click()
                    .moveByOffset(0, -50).click()
                    .moveByOffset(50, 0).click()
                    .moveByOffset(0, 50).click()
                    .moveByOffset(-50, 0).click()
                    .perform();
            Access.giveSomeTime(2);
            WebElement activeSaveButton = startPage.getSaveButton();
            assertTrue(activeSaveButton.isEnabled());
            activeSaveButton.click();

            ObjectAddForm addForm = new ObjectAddForm(webDriver);
            WebElement objLang = addForm.getLangText();
            WebElement objDescription = addForm.getDescriptionText();
            WebElement objAddress = addForm.getAddressText();
            WebElement objCategories = addForm.getCategoriesText();
            WebElement objBuilding = addForm.getBuildingText();
            WebElement objWikiLink = addForm.getWikiLinkText();
            assertAll(
                    () -> assertTrue(objLang.getText().contains("Язык:")),
                    () -> assertEquals("Описание", objDescription.getText()),
                    () -> assertEquals("Адрес", objAddress.getText()),
                    () -> assertEquals("Categories", objCategories.getText()),
                    () -> assertEquals("Здание", objBuilding.getText()),
                    () -> assertEquals("Ссылка на Википедию", objWikiLink.getText())
            );
        });
    }

}
