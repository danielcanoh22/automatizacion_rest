package co.com.udea.certificacion.checkinA.stepdefinitions;

import co.com.udea.certificacion.checkinA.tasks.ConnectTo;
import co.com.udea.certificacion.checkinA.tasks.PostTo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CreateProductStepDefinition {
    // Se declara un actor que simula las acciones de un usuario real
    Actor usuario = Actor.named("usuario");

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Given("I'm connected as a supplier")
    public void iAmConnectedAsASupplier(){
        // El "usuario" intenta conectarse al servicio
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I create a new product with the following details")
    public void iCreateANewProductWithTheFollowingDetails(DataTable productData){
        List<Map<String, String>> data = productData.asMaps(String.class, String.class);

        // Tomar la primera fila
        Map<String, String> body = data.get(0);

        // Ejecutar la Task para crear el producto
        usuario.attemptsTo(PostTo.service("/products", body));
    }

    @Then("the product is successfully created with these details")
    public void theProductIsSuccessfullyCreatedWithTheFollowingDetails(DataTable expectedData){
        // Convertir la tabla esperada a un mapa
        Map<String, String> expectedProductDetails = expectedData.asMaps(String.class, String.class).get(0);

        // Verificar que el código de estado sea 200 y que el cuerpo de la respuesta contenga los detalles del producto esperado
        usuario.should(seeThatResponse(response -> response.statusCode(200)
                .body("title", Matchers.equalTo(expectedProductDetails.get("title")))
                .body("price", Matchers.equalTo(expectedProductDetails.get("price")))
                .body("category", Matchers.equalTo(expectedProductDetails.get("category")))
                .body("description", Matchers.equalTo(expectedProductDetails.get("description")))
                .body("image", Matchers.equalTo(expectedProductDetails.get("image")))
        ));
    }
}
