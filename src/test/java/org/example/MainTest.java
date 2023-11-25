package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    void setUp(){
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
        driver.get("https://coffee-show.vercel.app/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Should open register modal after click on adicionar item button")
    void shouldOpenRegisterModalAfterClickOnAdicionarItemButton() throws InterruptedException {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("create")));
        button.click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        assertThat(modal).isNotNull();
    }

    @Test
    @DisplayName("Should close register modal after click on adicionar item button")
    void shouldCloseRegisterModalAfterClickOnFecharItemButton() throws InterruptedException {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("create")));
        button.click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        WebElement primeiroBotao = modal.findElement(By.tagName("button"));
        primeiroBotao.click();
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        });
    }

    @Test
    @DisplayName("Should alert if form is empty after click on Criar button")
    void shouldAlertEmptyFormAfterClickOnCriarButton() throws InterruptedException {
        driver.findElement(By.id("create")).click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        driver.findElement(By.cssSelector(".chakra-button.css-1lp2lqs")).click();
        WebElement errorDiv = driver.findElement(By.className("chakra-form__error-message"));
        assertThat(errorDiv).isNotNull();
    }
}