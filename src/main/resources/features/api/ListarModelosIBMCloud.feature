#language:pt
#encoding: utf-8
@APIS005
Funcionalidade: APISuite005 - Listar modelos na IBM Cloud

    Contexto: Preparar request
        Dado que defina o endpoint "op6851522v1"

    @ID-API005-001 @APICT001
    Cenario: APICT001 - OK - 200
        Dado que tenha a lista de dados do componente "WATSON_STUDIO" no endpoint "op5806077v3"
        Quando enviar um payload "MODELOS" com o código da lista de dados do componente "WATSON_STUDIO"
        Então deve retornar o código 200