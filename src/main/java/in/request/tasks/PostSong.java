package in.request.tasks;

import in.request.utils.TextFileReader;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;

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
                Post.to("/songs/detect")
                        .with(request -> request
                                .header("x-rapidapi-key", "a954888aa4mshcac6a6db889161cp143e4ajsnaba7813dd2d4")
                                .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                                .header("Content-Type", "text/plain")
                                .body(textoPlano)
                        )
        );
    }
}

