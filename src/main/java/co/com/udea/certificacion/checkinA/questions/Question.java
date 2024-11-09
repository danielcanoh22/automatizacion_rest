package co.com.udea.certificacion.checkinA.questions;

import net.serenitybdd.screenplay.Actor;

public class Question implements net.serenitybdd.screenplay.Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return true;
    }

}