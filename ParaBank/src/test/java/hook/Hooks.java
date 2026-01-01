package hook;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import stepdefinition.LoginStep;

public class Hooks {

    @After
    public void finalizarEscenario(Scenario scenario) {
        if (LoginStep.driver != null) {
            // 1. Tomar la captura mientras la sesión sigue viva
            final byte[] screenshot = ((TakesScreenshot) LoginStep.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Evidencia Final");

            // 2. Ahora sí, cerrar la sesión
            LoginStep.driver.quit();
            LoginStep.driver = null; // Buena práctica: resetear a null
        }
    }
}