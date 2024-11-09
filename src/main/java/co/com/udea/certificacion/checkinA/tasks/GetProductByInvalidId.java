package co.com.udea.certificacion.checkinA.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetProductByInvalidId implements Task {

    private final String invalidId; // ID inválido que se usará

    public GetProductByInvalidId(String invalidId) {
        this.invalidId = invalidId;
    }

    // Método que realiza la acción de obtener un producto usando un ID inválido
    @Override
    public <T extends Actor> void performAs(T actor) {
        // Construcción del endpoint con el ID inválido
        String endpoint = "/products/" + invalidId;

        // El actor realiza la solicitud GET al endpoint formado con el ID inválido.
        actor.attemptsTo(
                Get.resource(endpoint).with(
                        request -> request.relaxedHTTPSValidation()
                )
        );
    }

    // Método estático para la creación de la tarea 'GetProductByInvalidId'
    public static GetProductByInvalidId withId(String invalidId) {
        return Tasks.instrumented(GetProductByInvalidId.class, invalidId);
    }
}
