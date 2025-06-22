
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