package com.parabank.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait driverWait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "customer.firstName")
    private WebElement txtFirstname;
    @FindBy(id = "customer.lastName")
    private WebElement txtLastname;
    @FindBy(id = "customer.address.street")
    private WebElement txtStreet;
    @FindBy(id = "customer.address.city")
    private WebElement txtCity;
    @FindBy(id = "customer.address.state")
    private WebElement txtState;
    @FindBy(id = "customer.address.zipCode")
    private WebElement txtZipCode;
    @FindBy(id = "customer.ssn")
    private WebElement txtSsn;
    @FindBy(id = "customer.phoneNumber")
    private WebElement txtPhonenumber;
    @FindBy(id = "customer.username")
    private WebElement txtUsername;
    @FindBy(id = "customer.password")
    private WebElement txtPassword;
    @FindBy(id = "repeatedPassword")
    private WebElement txtConfirmPassword;
    @FindBy(css = "#customerForm table tbody tr:nth-child(13) td:nth-child(2) > input")
    private WebElement btnRegister;
    @FindBy(linkText = "Log Out")
    private WebElement lblLogout;

    public void registerUser(String user, String pass) {
        txtFirstname.sendKeys("DEMO");
        txtLastname.sendKeys("AUTOMATION");
        txtStreet.sendKeys("QA 27 AV");
        txtCity.sendKeys("QA CITY");
        txtState.sendKeys("LIMA");
        txtPhonenumber.sendKeys("987451236");
        txtZipCode.sendKeys("15236");
        txtSsn.sendKeys("00-00-0000");
        txtUsername.sendKeys(user);
        txtPassword.sendKeys(pass);
        txtConfirmPassword.sendKeys(pass);
        driverWait.until(ExpectedConditions.elementToBeClickable(btnRegister));
        btnRegister.click();
    }

    public void logOut() {
        driverWait.until(ExpectedConditions.elementToBeClickable(lblLogout));
        lblLogout.click();
    }
}
