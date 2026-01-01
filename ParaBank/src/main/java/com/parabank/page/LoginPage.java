package com.parabank.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait driverWait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    private WebElement txtUsername;
    @FindBy(name = "password")
    private WebElement txtPassword;
    @FindBy(css = "#loginPanel form div:nth-of-type(3) > input")
    private WebElement btnLogin;
    @FindBy(linkText = "Register")
    private WebElement lblRegister;

    public void launchApp(String url) {
        driver.get(url);
    }

    public RegisterPage clickRegister() {
        driverWait.until(ExpectedConditions.elementToBeClickable(lblRegister)).click();
        return new RegisterPage(driver);
    }

    public void login(String user, String pass) {
        driverWait.until(ExpectedConditions.visibilityOf(txtUsername)).sendKeys(user);
        txtPassword.sendKeys(pass);
        btnLogin.click();
    }
}
