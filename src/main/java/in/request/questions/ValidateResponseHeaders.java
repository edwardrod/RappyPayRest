package in.request.questions;


import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ValidateResponseHeaders {

    public static Question<Boolean> areValid() {
        return actor -> {
            Response response = SerenityRest.lastResponse();

            assertThat("Connection debe ser keep-alive",
                    response.getHeader("Connection"), equalToIgnoringCase("keep-alive"));

            assertThat("Allow-Methods debe incluir GET y POST",
                    response.getHeader("Access-Control-Allow-Methods"), containsString("GET"));

            assertThat("X-RapidAPI-Version debe ser 1.2.8",
                    response.getHeader("X-RapidAPI-Version"), equalTo("1.2.8"));

            assertThat("X-RapidAPI-Region debe ser us-east-1",
                    response.getHeader("X-RapidAPI-Region"), containsString("us-east-1"));

            int limit = Integer.parseInt(response.getHeader("X-RateLimit-Requests-Limit"));
            int remaining = Integer.parseInt(response.getHeader("X-RateLimit-Requests-Remaining"));
            assertThat("El límite debe ser 500", limit, equalTo(500));
            assertThat("Los remaining deben ser menores o iguales al límite", remaining, lessThanOrEqualTo(limit));

            return true;
        };
    }
}

