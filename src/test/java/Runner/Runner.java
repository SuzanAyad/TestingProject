package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@Regression",
        features = "src/main/resources/Features",
        glue = "Steps"
)

public class Runner extends AbstractTestNGCucumberTests {
}
