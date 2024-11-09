package co.com.udea.certificacion.checkinA.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class ConnectTo implements Task {

    private EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    String url_api;

    public ConnectTo() {
        url_api = environmentVariables.getProperty("webdriver.base.url");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.whoCan(CallAnApi.at(url_api));
    }

    public static ConnectTo theService() {
        return Tasks.instrumented(ConnectTo.class);
    }
}