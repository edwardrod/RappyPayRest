@AcceptanceTest
Feature: Validación de endpoints de la API pública de Shazam

  Scenario: Obtener detalles de una cancion por ID
    When el usuario consulta los detalles de la cancion con ID "1217912247"
    Then la respuesta debe tener el código 200


  Scenario Outline: : Detectar una cancion con texto plano
    When el usuario envia el texto "<relativePath>" para deteccion
    Then la respuesta debe tener el código 200
    And la respuesta debe tener la estructura esperada
    Examples:
    |relativePath|
    |data/song_payload.txt|