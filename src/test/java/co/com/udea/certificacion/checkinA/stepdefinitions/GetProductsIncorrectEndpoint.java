package co.com.udea.certificacion.checkinA.stepdefinitions;

import co.com.udea.certificacion.checkinA.tasks.ConnectTo;
import co.com.udea.certificacion.checkinA.tasks.GetFromIncorrectEndpoint;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetProductsIncorrectEndpoint {
    // Se declara un actor que simula las acciones de un usuario real
    Actor usuario = Actor.named("usuario");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Given("I am connected as a user.")
    public void iAmConnectedAsAUserIncorrectEndpoint() {
        // El "usuario" intenta conectarse al servicio
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I send a GET request to retrieve all the products to the wrong route")
    public void iSendAGETRequestToRetrieveAllTheProductsWrongRoute() {
        // El "usuario" intenta realizar una solicitud GET a un endpoint incorrecto
        usuario.attemptsTo(GetFromIncorrectEndpoint.product());
    }

    @Then("the API should respond with a 404 status code indicating that the resource was not found")
    public void iShouldReceiveAResponseWithStatusCodeNotFound() {
        // El método "seeThatResponse" valida la respuesta de la API y confirma que el código de estado sea 404.
        usuario.should(
                seeThatResponse("El servidor responde con error 404",
                        response -> response.statusCode(404)
                )
        );
    }
}
