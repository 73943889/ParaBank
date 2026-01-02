package hook;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import stepdefinition.LoginStep;

public class Hooks {

    @After
    public void finalizarEscenario(Scenario scenario) {
        if (LoginStep.driver != null) {
            final byte[] screenshot = ((TakesScreenshot) LoginStep.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Evidencia Final");
            LoginStep.driver.quit();
            LoginStep.driver = null;
        }
    }
}