package co.com.udea.certificacion.checkinA.stepdefinitions;

import co.com.udea.certificacion.checkinA.tasks.ConnectTo;
import co.com.udea.certificacion.checkinA.tasks.GetProductByInvalidId;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetProductByInvalidIdStepDefinition {
    // Se declara un actor que simula las acciones de un usuario real
    Actor usuario = Actor.named("usuario");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Given("I am connected as a supplier \\(invalid id)")
    public void iAmConnectedAsASupplier() {
        // El "usuario" intenta conectarse al servicio
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I send a GET request to retrieve a product with an invalid ID {string}")
    public void iSendAGetRequestToRetrieveAProductWithAnInvalidID(String invalidId) {
        // El "usuario" intenta realizar una solicitud GET con un id inválido
        usuario.attemptsTo(GetProductByInvalidId.withId(invalidId));
    }

    @Then("the API should respond with status code 200")
    public void theApiShouldRespondWithStatusCode200() {
        // El método "seeThatResponse" valida la respuesta de la API y confirma que el código de estado sea 200.
        usuario.should(seeThatResponse(response -> response.statusCode(200)));
    }

    @Then("the response should indicate that the product was not found")
    public void theResponseShouldIndicateThatTheProductWasNotFound() {
        // Verificar que el cuerpo de la respuesta esté vacío
        usuario.should(seeThatResponse(response ->
                response.body(Matchers.equalTo(""))
        ));
    }
}
