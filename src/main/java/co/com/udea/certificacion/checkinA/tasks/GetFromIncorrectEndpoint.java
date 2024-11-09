package co.com.udea.certificacion.checkinA.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetFromIncorrectEndpoint implements Task {

    private final String incorrectEndpoint;

    public GetFromIncorrectEndpoint(String incorrectEndpoint) {
        this.incorrectEndpoint = incorrectEndpoint;
    }

    public static GetFromIncorrectEndpoint product() {
        return instrumented(GetFromIncorrectEndpoint.class, "/product"); // Endpoint incorrecto
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(incorrectEndpoint)
        );
    }
}
