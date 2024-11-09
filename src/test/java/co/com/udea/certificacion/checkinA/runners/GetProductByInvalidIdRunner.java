package co.com.udea.certificacion.checkinA.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/get_product_invalid_id.feature",
        glue = "co.com.udea.certificacion.checkinA.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class GetProductByInvalidIdRunner {}