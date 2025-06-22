package in.request.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetSongDetails implements Task {

    private final String songId;

    public GetSongDetails(String songId) {
        this.songId = songId;
    }

    public static GetSongDetails byId(String songId) {
        return Tasks.instrumented(GetSongDetails.class, songId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/songs/v2/get-details")
                        .with(request -> request
                                .queryParam("id", songId)
                                .queryParam("l", "en-US")
                                .header("X-Rapidapi-Key", "a954888aa4mshcac6a6db889161cp143e4ajsnaba7813dd2d4")
                                .header("X-Rapidapi-Host", "shazam.p.rapidapi.com")
                        )
        );
    }
}

