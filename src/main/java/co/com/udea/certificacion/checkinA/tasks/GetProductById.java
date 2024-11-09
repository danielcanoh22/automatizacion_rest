package co.com.udea.certificacion.checkinA.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetProductById implements Task {

    private final String productId;

    public GetProductById(String productId) {
        this.productId = productId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = "/products/" + productId; // Construimos la URL con el ID del producto
        actor.attemptsTo(
                Get.resource(url).with(
                        request -> request.relaxedHTTPSValidation()
                                .accept("application/json")
                )
        );
    }

    public static GetProductById withId(String productId) {
        return Tasks.instrumented(GetProductById.class, productId);
    }
}
