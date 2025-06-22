package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/shazam.feature",
        glue = "stepdefinitions",
        tags = "@Shazam",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ShazamApiRunner {}
