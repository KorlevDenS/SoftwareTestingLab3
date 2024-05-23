package org.korolev.dens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriesTest {

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

    @ParameterizedTest(name = "category {0}: {1}")
    @DisplayName("Search by category")
    @CsvFileSource(resources = "/categories.csv", numLinesToSkip = 1, delimiter = ';')
    void doCategoriesSearch(int index, String name) {
        this.drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(startPage.getCategoriesDropdown())
                    .moveToElement(startPage.getCategoryDropdownVariant(index)).click().perform();
            WebElement content = startPage.getCategoryContent();
            assertAll(
                    () -> assertTrue(content.isDisplayed()),
                    () -> assertEquals("Просмотр категории", startPage.getCategoryLookUpText().getText()),
                    () -> assertEquals(name, startPage.getCategoryContentName().getText())
            );
            webDriver.quit();
        });
    }

    @Test
    void checkCategory() {
        this.drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(startPage.getCategoriesDropdown())
                    .moveToElement(startPage.getCategoryDropdownVariant(1)).click().perform();
            Access.giveSomeTime(2);
            actions.moveToElement(startPage.getCategoryContentName()).click().perform();
            webDriver.switchTo().frame(Access.getElement(
                    webDriver, By.xpath("./html/body/div[1]/div[1]/div[3]/div[16]/iframe")
            ));

            CategoryModal categoryModal = new CategoryModal(webDriver);
            WebElement description = categoryModal.getDescription();
            assertTrue(description.isDisplayed());
            assertEquals("Стадион (греч. στάδιον, «ристалище») — сооружение для спортивных целей. " +
                    "Обычно включает большое поле и места для зрителей, расположенные ступенчато вокруг поля. " +
                    "Также часто содержит дополнительные площадки и вспомогательные помещения для тренировки и " +
                    "переодевания команд. Стадионы используются не только для различных видов спорта на открытом " +
                    "воздухе (например, лёгкой атлетики и футбола), но также для концертов и других мероприятий.",
                    description.getText());
            webDriver.switchTo().defaultContent();
            actions.moveToElement(webDriver.findElement(
                    By.xpath("./html/body/div[1]/div[1]/div[3]/div[16]/div/span[1]")))
                    .click().perform();

            actions.moveToElement(startPage.getActivityButton()).click().perform();
            CategoryPanel categoryPanel = new CategoryPanel(webDriver);
            WebElement activity = categoryPanel.getName();
            assertEquals("Категория: стадион", activity.getText());
            webDriver.quit();
        });
    }

}
