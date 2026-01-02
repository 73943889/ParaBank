package stepdefinition;

import com.parabank.page.LoginPage;
import com.parabank.page.RegisterPage;
import com.parabank.util.ConfigHandler;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStep {
    public static WebDriver driver;
    LoginPage loginPage;
    RegisterPage registerPage;

    private static String sharedUser;
    private static final String sharedPass = "P@sswordDemo2025";

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("que el usuario genera y registra una cuenta nueva")
    public void registrarCuentaNueva() {
        sharedUser = "U_QABOT" + (System.currentTimeMillis() % 1_000_000_000L) + (int) (Math.random() * 1000);
        loginPage = new LoginPage(driver);
        loginPage.launchApp("https://parabank.parasoft.com/parabank/index.htm");
        registerPage = loginPage.clickRegister();
        registerPage.registerUser(sharedUser, sharedPass);
        ConfigHandler.setTestData(sharedUser,sharedPass);
        registerPage.logOut();
    }

    @When("inicia sesion con los datos generados")
    public void iniciarSesion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        loginPage.login(sharedUser, sharedPass);
    }

    @Then("valido el ingreso exitoso visualizando {string}")
    public void validarIngreso(String mensajeEsperado) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean visualizaMensaje = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    org.openqa.selenium.By.tagName("body"), mensajeEsperado));
            System.out.println("LOG: Login exitoso para el usuario dinámico: " + sharedUser);
        } catch (Exception e) {
            org.testng.Assert.fail("ERROR: No se encontró '" + mensajeEsperado + "' tras el login. Posible fallo de credenciales o lentitud del sitio.");
        }
    }

}
