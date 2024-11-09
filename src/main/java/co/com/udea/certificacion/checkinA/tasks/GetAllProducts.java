package co.com.udea.certificacion.checkinA.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetAllProducts implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        String endpoint = "/products";

        actor.attemptsTo(Get.resource(endpoint).with(
                request -> request.relaxedHTTPSValidation()
                        .accept("application/json")
        ));
    }

    public static GetAllProducts fetch() {
        return Tasks.instrumented(GetAllProducts.class);
    }
}
