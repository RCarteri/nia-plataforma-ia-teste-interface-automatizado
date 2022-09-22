#language:pt
#encoding: utf-8
@APIS002
Funcionalidade: APISuite002 - Listar recursos BB na IBM Cloud

    @ID-API002-001 @APICT001
    Esquema do Cenário: APICT001 - OK - 200
        Dado que defina o endpoint "<endpoint>"
        Quando enviar um payload "<payload>"
        Então deve retornar o código 200
        Exemplos:
            | payload      | endpoint    |
            | OK-Studio    | op5806077v2 |
            | OK-Assistant | op5806077v2 |
            | OK-Catalog   | op5806077v2 |
            | OK-Storage   | op5806077v2 |
            | OK-Modelo    | op5839181v1 |
            | OK-Grupos    | op5839181v1 |
            | OK-Sigla     | op5806077v3 |