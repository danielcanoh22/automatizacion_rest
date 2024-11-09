package co.com.udea.certificacion.checkinA.tasks;


import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostTo implements Task {
    private String endpoint; // Ruta del endpoint a la que se enviará la solicitud POST
    private Map<String, String> body; // Datos del cuerpo de la solicitud en formato clave-valor

    public PostTo(String endpoint, Map<String, String> body) {
        this.endpoint = endpoint;
        this.body = body;
    }

    // Método estático para crear una instancia de la clase PostTo con el endpoint y el cuerpo de la solicitud
    public static PostTo service(String endpoint, Map<String, String> body) {
        return instrumented(PostTo.class, endpoint, body);
    }

    // Enviar la solicitud POST
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endpoint) // Enviar una solicitud POST a la ruta especificada
                        .with(request -> request
                                .contentType(ContentType.JSON) // Define el tipo de contenido como JSON
                                .body(body) // Agrega el cuerpo de la solicitud con los datos proporcionados
                                .relaxedHTTPSValidation() // Permite validaciones HTTPS relajadas
                        )
        );
    }
}
