
# RappyPayRest

Proyecto de automatización de pruebas API utilizando **Serenity BDD**, **Screenplay Pattern**, **RestAssured** y **Cucumber**.  
Apunta a validar endpoints públicos como el de detección de canciones de la API de Shazam en RapidAPI.

## 🧪 Tecnologías y Frameworks

- Java 17
- Gradle 8.14
- Serenity BDD 4.1.14
- Cucumber 7.17.0
- Screenplay Pattern
- RestAssured
- JUnit 4

---

## 📁 Estructura del Proyecto

```
📦 RappyPayRest
├── src
│   ├── main
│   │   └── java
│   │       └── in.request
│   │           ├── models/
│   │           ├── questions/
│   │           ├── tasks/
│   │           └── utils/
│   └── test
│       ├── java
│       │   ├── runners/
│       │   └── stepdefinitions/
│       └── resources
|           ├── data/
│           └── features/
├── build.gradle
└── README.md
```

---

## ▶️ Ejecución de pruebas

Para correr los escenarios de Cucumber y generar reportes de Serenity, usa:

```bash
gradle clean test aggregate
```

> El reporte se generará en:  
> `build/reports/serenity/index.html`

---

## 🧾 Ejemplo de Feature

```gherkin
Feature: Validación de endpoints de la API pública de Shazam

  @Shazam
  Scenario: Detectar una canción con texto plano
    When el usuario envía el texto "data/song_payload.txt" para detección
    Then la respuesta debe tener el código 200
    And la respuesta debe tener la estructura esperada

  @Shazam
  Scenario: Obtener detalles de una canción por ID
    When el usuario consulta los detalles de la canción con ID "1217912247"
    Then la respuesta debe tener el código 200
    And la respuesta debe tener la estructura
```

---

## ⚙️ Configuración

Asegúrate de establecer correctamente la URL base y los headers de autenticación:

```java
OnStage.setTheStage(new OnlineCast());
Actor actor = OnStage.theActorCalled("Eduar")
                     .whoCan(CallAnApi.at("https://shazam.p.rapidapi.com"));
```

Headers requeridos:

```java
.header("X-RapidAPI-Key", "<TU_API_KEY>")
.header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
.contentType(ContentType.TEXT)
```

---

## ✅ Validaciones realizadas

- Validación del código de estado HTTP
- Validación de estructura de `track`, `matches`, `images`, etc.
- Validación de headers importantes como:
    - `X-RapidAPI-Version`
    - `X-RateLimit-Requests-Limit`
    - `Server`
    - `Connection`

---

## 📌 Notas

- Para enviar audio, se utiliza contenido Base64 proveniente de un archivo `.txt` ubicado en `src/test/resources/data/song_payload.txt`.
- El proyecto ignora la carpeta `/target` mediante `.gitignore`.

---

## ✍ Autor

Proyecto desarrollado por **[Edward Rodríguez](https://github.com/edwardrod)** como parte del reto técnico QA para RappyPay 2025.
