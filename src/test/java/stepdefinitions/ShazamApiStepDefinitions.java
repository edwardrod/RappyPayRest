package stepdefinitions;

import in.request.questions.ResponseCode;
import in.request.questions.ValidateResponseHeaders;
import in.request.questions.ValidateShazamSongByIdResponse;
import in.request.tasks.GetSongDetails;
import in.request.tasks.PostSong;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class ShazamApiStepDefinitions {

    private Actor actor;


    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("Eduar")
                .whoCan(CallAnApi.at("https://shazam.p.rapidapi.com"));
    }

    @When("el usuario consulta los detalles de la canción con ID {string}")
    public void consultarCancionPorId(String id) {
        actor.attemptsTo(GetSongDetails.byId(id));
    }

    @When("el usuario envía el texto {string} para detección")
    public void detectarCancion(String path) {
        actor.attemptsTo(PostSong.using(path));
    }

    @Then("la respuesta debe tener el código {int}")
    public void validarCodigoRespuesta(int codigoEsperado) {
        actor.should(
                seeThat("el código de respuesta", ResponseCode.is(), equalTo(codigoEsperado))
        );
    }

    @And("la respuesta debe tener la estructura")
    public void laRespuestaDebeTenerLaEstructura() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("la estructura de respuesta", ValidateShazamSongByIdResponse.isValid())
        );
    }


    @And("la respuesta debe tener la estructura esperada")
    public void laRespuestaDebeTenerLaEstructuraEsperada() {
        actor.should(
                seeThat("la estructura de respuesta", ValidateResponseHeaders.areValid())
        );
    }
}
