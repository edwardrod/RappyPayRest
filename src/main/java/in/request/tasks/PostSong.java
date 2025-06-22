package in.request.tasks;

import in.request.utils.TextFileReader;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class PostSong implements Task {

    private final String filePath;

    public PostSong(String songId) {
        this.filePath = songId;
    }

    public static PostSong using(String textPlano) {
        return Tasks.instrumented(PostSong.class, textPlano);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String textoPlano = TextFileReader.from(filePath);

        actor.attemptsTo(
                Get.resource("/songs/v2/get-details")
                        .with(request -> request
                                .queryParam("timezone", "America%2FChicago")
                                .queryParam("locale", "en-US")
                                .header("X-Rapidapi-Key", "a954888aa4mshcac6a6db889161cp143e4ajsnaba7813dd2d4")
                                .header("X-Rapidapi-Host", "shazam.p.rapidapi.com")
                                .header("Content-Type", "application/json")
                                .body(textoPlano)
                        )
        );
    }
}

