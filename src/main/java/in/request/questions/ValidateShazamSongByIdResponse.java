package in.request.questions;


import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ValidateShazamSongByIdResponse {

    public static Question<Boolean> isValid() {
        return actor -> {
            var response = SerenityRest.lastResponse().jsonPath();

            // Validación básica de raíz
            List<Map<String, Object>> data = response.getList("data");
            assertThat("La lista 'data' debe existir", data, is(notNullValue()));
            assertThat("Debe haber al menos un elemento en 'data'", data.size(), greaterThan(0));

            // Validación de campos de attributes
            String basePath = "data[0].attributes.";
            assertThat(response.getString(basePath + "albumName"), is(not(emptyOrNullString())));
            assertThat(response.getString(basePath + "name"), is(not(emptyOrNullString())));
            assertThat(response.getString(basePath + "artistName"), is(not(emptyOrNullString())));
            assertThat(response.getInt(basePath + "trackNumber"), greaterThan(0));
            assertThat(response.getInt(basePath + "durationInMillis"), greaterThan(0));
            assertThat(response.getString(basePath + "url"), startsWith("https://"));
            assertThat(response.getString(basePath + "releaseDate"), is(not(emptyOrNullString())));
            assertThat(response.getString(basePath + "isrc"), is(not(emptyOrNullString())));

            // Validación de artwork
            assertThat(response.getString(basePath + "artwork.url"), containsString("{w}x{h}"));

            // Validación de genreNames
            List<String> genres = response.getList(basePath + "genreNames");
            assertThat("Debe contener al menos un género", genres, is(not(empty())));

            // Validación de previews
            List<Map<String, Object>> previews = response.getList(basePath + "previews");
            assertThat("Debe contener al menos una preview", previews, is(not(empty())));
            assertThat(previews.get(0).get("url").toString(), startsWith("https://"));

            // Validación de relationships
            assertThat(response.getList("data[0].relationships.albums.data"), is(not(empty())));
            assertThat(response.getList("data[0].relationships.artists.data"), is(not(empty())));

            // Validación de meta
            assertThat(response.get("data[0].meta.contentVersion.RTCI"), is(notNullValue()));
            assertThat(response.get("data[0].meta.contentVersion.MZ_INDEXER"), is(notNullValue()));

            return true;
        };
    }
}

