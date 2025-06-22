package in.request.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class ValidateShazamResponseStructure {

    public static Question<Boolean> isValid() {
        return actor -> {
            var response = SerenityRest.lastResponse().jsonPath();

            // Validación de campos raíz
            assertThat("matches está presente y es una lista", response.getList("matches"), is(notNullValue()));
            assertThat("timestamp está presente", response.getLong("timestamp"), is(notNullValue()));
            assertThat("timezone está presente", response.getString("timezone"), is(not(emptyOrNullString())));
            assertThat("track.title está presente", response.getString("track.title"), is(not(emptyOrNullString())));
            assertThat("track.subtitle está presente", response.getString("track.subtitle"), is(not(emptyOrNullString())));
            assertThat("track.share.href es una URL válida", response.getString("track.share.href"), startsWith("https://"));

            // Validación de campos dentro de un objeto en lista
            List<Map<String, Object>> matches = response.getList("matches");
            assertThat("cada match tiene id", matches.get(0).get("id"), is(notNullValue()));
            assertThat("cada match tiene offset", matches.get(0).get("offset"), is(notNullValue()));

            // Validar estructura básica de images
            assertThat("track.images.coverart existe", response.getString("track.images.coverart"), containsString("http"));

            return true;
        };
    }
}
