package org.korolev.dens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthTest {

    private List<WebDriver> drivers;

    @BeforeEach
    void setDrivers() {
        this.drivers = Access.getDrivers();
    }

    @Test
    void openCite() {
        this.drivers.forEach(this::openWebCite);
    }

    private void openWebCite(WebDriver driver) {
        driver.get(Access.BASE_URL);
        String title = driver.getTitle();
        assertEquals("Wikimapia — опишем весь мир!", title);
        driver.quit();
    }

    @Test
    void tryLogin() {
        drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            webDriver.get(Access.BASE_URL);
            startPage.login(Access.CORRECT_LOGIN, Access.CORRECT_PASSWORD);
            webDriver.quit();
        });
    }

    @Test
    void tryLoginWithEmail() {
        drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            webDriver.get(Access.BASE_URL);
            startPage.login(Access.CORRECT_EMAIL, Access.CORRECT_PASSWORD);
            webDriver.quit();
        });
    }

    @Test
    void tryLoginWithWrongPassword() {
        drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            webDriver.get(Access.BASE_URL);
            Throwable throwable = assertThrows(Exception.class,
                    () -> startPage.login(Access.CORRECT_LOGIN, Access.WRONG_PASSWORD));
            assertEquals(throwable.getMessage(), "Not logged in for 10 seconds");
            webDriver.quit();
        });
    }

    @Test
    void tryLogout() {
        drivers.parallelStream().forEach(webDriver -> {
            StartPage startPage = new StartPage(webDriver);
            webDriver.get(Access.BASE_URL);
            startPage.login(Access.CORRECT_LOGIN, Access.CORRECT_PASSWORD);
            startPage.logout();
            try {
                Thread.sleep(Duration.ofSeconds(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WebElement loginButtonText = Access.getElement(
                    webDriver, By.xpath("./html/body/div[1]/div[2]/div[1]/div/div[1]/ul/li[3]/a")
            );
            assertEquals(loginButtonText.getText(), "Войти");
            webDriver.quit();
        });
    }

}
