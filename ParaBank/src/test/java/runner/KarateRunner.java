package runner;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class KarateRunner {
    @Test
    public void testAll() throws IOException {

        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/testdata.properties"));

        System.setProperty("testUser", props.getProperty("test.username"));
        System.setProperty("testPass", props.getProperty("test.password"));

        Results results = Runner.path("classpath:api/feature")
                .tags("@E2E")
                .outputCucumberJson(true)
                .parallel(1);
        generateReport(results.getReportDir());
        Assert.assertEquals(results.getFailCount(), 0, results.getErrorMessages());
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "WebParabank");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
