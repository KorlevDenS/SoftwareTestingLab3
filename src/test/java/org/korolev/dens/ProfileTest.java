package org.korolev.dens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private List<WebDriver> drivers;

    @BeforeEach
    void setDriversAndLogin() {
        this.drivers = Access.getDrivers();
        this.drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            webDriver.get(Access.BASE_URL);
            startPage.login(Access.CORRECT_LOGIN, Access.CORRECT_PASSWORD);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(Access.getElement(webDriver,
                    By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[3]/a"))).perform();
            actions.moveToElement(Access.getElement(webDriver,
                            By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[3]/ul/li[1]")))
                    .click().perform();
            webDriver.switchTo().frame(Access.getElement(
                    webDriver, By.xpath("./html/body/div[1]/div[1]/div[3]/div[16]/iframe")
            ));
        });
    }

    @Test
    void checkInstruments() {
        this.drivers.parallelStream().forEach(webDriver -> {
            ProfileModal profileModal = new ProfileModal(webDriver);
            assertDoesNotThrow(() -> profileModal.getInstrumentsButton().click());
            reSwitchToProfileFrame(webDriver);
            assertAll(
                    () -> assertEquals("Для всех пользователей", profileModal
                            .getForAllUsersInstrumentsText().getText()),
                    () -> assertEquals("Уровень 1+", profileModal.getLevelPlus1InstrumentsText().getText())
            );
            webDriver.quit();
        });
    }

    @Test
    void checkStatistics() {
        this.drivers.parallelStream().forEach(webDriver -> {
            ProfileModal profileModal = new ProfileModal(webDriver);
            assertDoesNotThrow(() -> profileModal.getStatisticsButton().click());
            reSwitchToProfileFrame(webDriver);
            assertAll(
                    () -> assertEquals("Всего правок:", profileModal.getCorrectionsAtAllText().getText()),
                    () -> assertEquals("Добавлено отзывов о других пользователях:",
                            profileModal.getFeedBackText().getText())
            );
            webDriver.quit();
        });
    }

    @Test
    void checkAwards() {
        this.drivers.parallelStream().forEach(webDriver -> {
            ProfileModal profileModal = new ProfileModal(webDriver);
            assertDoesNotThrow(() -> profileModal.getAwardsButton().click());
            reSwitchToProfileFrame(webDriver);
            assertEquals("Ваши награды", profileModal.getYourAwardsText().getText());
            webDriver.quit();
        });
    }

    @Test
    void checkMessages() {
        this.drivers.parallelStream().forEach(webDriver -> {
            ProfileModal profileModal = new ProfileModal(webDriver);
            assertDoesNotThrow(() -> profileModal.getMessagesButton().click());
            reSwitchToProfileFrame(webDriver);
            assertEquals("Информационные сообщения", profileModal.getInformationMessagesText().getText());
            webDriver.quit();
        });
    }

    private void reSwitchToProfileFrame(WebDriver webDriver) {
        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(Access.getElement(
                webDriver, By.xpath("./html/body/div[1]/div[1]/div[3]/div[16]/iframe")
        ));
    }

    @Test
    void openProfile() {
        this.drivers.parallelStream().forEach(webDriver -> {
            ProfileModal profileModal = new ProfileModal(webDriver);
            WebElement userName = profileModal.getUserNameText();
            WebElement profileTitle = profileModal.getProfileTitle();
            WebElement levelTitle = profileModal.getLevelTitle();
            WebElement level = profileModal.getLevel();
            WebElement ratingTitle = profileModal.getRatingTitle();
            WebElement rating = profileModal.getRating();
            assertAll (
                    () -> assertEquals(userName.getText(), Access.CORRECT_LOGIN),
                    () -> assertEquals(profileTitle.getText(), "Профиль пользователя"),
                    () -> assertEquals(levelTitle.getText(), "Уровень пользователя:"),
                    () -> assertEquals(level.getText(), "1"),
                    () -> assertEquals(ratingTitle.getText(), "Место в рейтинге:"),
                    () -> assertDoesNotThrow(() -> {
                        Integer.parseInt(rating.getText());
                    })
            );
            webDriver.quit();
        });
    }

}
