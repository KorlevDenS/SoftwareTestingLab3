package org.korolev.dens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Access {

    public static final String BASE_URL = "https://www.wikimapia.org/";

    public static final String CORRECT_LOGIN = "dragonfruit";
    public static final String CORRECT_EMAIL = "den.korolev.1999@bk.ru";

    public static final String CORRECT_PASSWORD = "333165";
    public static final String WRONG_PASSWORD = "666666";

    public static WebElement getElement(WebDriver driver, By selector) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void giveSomeTime(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<WebDriver> getDrivers() {
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get("src/wikimapia.properties"));
            for (String property : properties) {
                if (property.startsWith("WEB_DRIVER")) {
                    switch (property.toLowerCase().split("=")[1]) {
                        case "chrome":
                            drivers.add(getChromeDriver());
                            return drivers;
                        case "firefox":
                            drivers.add(getFirefoxDriver());
                            return drivers;
                        case "both":
                            drivers.add(getChromeDriver());
                            drivers.add(getFirefoxDriver());
                            return drivers;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getClass() + ": " + e.getMessage());
        }
        throw new RuntimeException("Web driver is not specified");
    }

    private static ChromeDriver getChromeDriver() {
        return new ChromeDriver();
    }

    private static FirefoxDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

}
