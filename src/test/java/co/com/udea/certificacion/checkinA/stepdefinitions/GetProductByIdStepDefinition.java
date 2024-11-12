package co.com.udea.certificacion.checkinA.stepdefinitions;

import co.com.udea.certificacion.checkinA.tasks.ConnectTo;
import co.com.udea.certificacion.checkinA.tasks.GetProductById;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetProductByIdStepDefinition {
    // Se declara un actor que simula las acciones de un usuario real
    Actor usuario = Actor.named("usuario");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Given("I am connected as a supplier")
    public void iAmConnectedAsASupplier() {
        // El "usuario" intenta conectarse al servicio
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I send a GET request to retrieve the product by its ID {string}")
    public void iSendAGETRequestToRetrieveTheProductByItsID(String productId) {
        // Se intenta obtener un producto con el ID 5
        usuario.attemptsTo(GetProductById.withId(productId));
    }

    @Then("I should receive a response with status code 200")
    public void iShouldReceiveAResponseWithStatusCode() {
        // Verifica que la respuesta tenga el código de estado 200 (OK)
        usuario.should(seeThatResponse(response -> response.statusCode(200)));
    }

    @Then("the product details should be returned")
    public void theProductDetailsShouldBeReturned() {
        // Verificar que la respuesta contiene los detalles del producto esperados
        usuario.should(seeThatResponse(response -> response
                .body("title", Matchers.equalTo("John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet"))
                .body("price", Matchers.equalTo(695))
                .body("category", Matchers.equalTo("jewelery"))
                .body("description", Matchers.equalTo("From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection."))
                .body("image", Matchers.equalTo("https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"))
        ));
    }
}
