package co.com.udea.certificacion.checkinA.stepdefinitions;

import co.com.udea.certificacion.checkinA.tasks.ConnectTo;
import co.com.udea.certificacion.checkinA.tasks.GetAllProducts;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetAllProductsStepDefinition {
    // Se declara un actor que simula las acciones de un usuario real
    Actor usuario = Actor.named("usuario");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    @Given("I am connected as a user")
    public void iAmConnectedAsAUser() {
        // El "usuario" intenta conectarse al servicio
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I send a GET request to retrieve all the products")
    public void iSendAGETRequestToRetrieveAllTheProducts() {
        // Se intenta obtener todos los productos
        usuario.attemptsTo(GetAllProducts.fetch());
    }

    @Then("I should receive a response with status code 200.")
    public void iShouldReceiveAResponseWithStatusCodeAll() {
        // Verifica que la respuesta tenga el código de estado 200 (OK)
        usuario.should(seeThatResponse(response -> response.statusCode(200)));
    }

    @Then("all the products should be shown")
    public void allTheProductsShouldBeShown() {
        // Verificar que la respuesta contiene 20 productos (la API devuelve solo 20)
        usuario.should(
                seeThatResponse(response -> response.body("size()", Matchers.is(20)))
        );
    }
}
