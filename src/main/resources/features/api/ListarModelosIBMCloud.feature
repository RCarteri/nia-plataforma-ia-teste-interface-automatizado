#language:pt
#encoding: utf-8
@APIS005
Funcionalidade: APISuite005 - Listar modelos na IBM Cloud

    @ID-API005-001 @APICT001
    Cenario: APICT001 - OK - 200
        Dado que tenha a lista de retorno do "WATSON_STUDIO" no endpoint "op5806077v3"
        E que defina o endpoint "op6851522v1"
        Quando enviar um payload "MODELOS" com o código do "projeto" selecionado aleatóriamente
        Então deve retornar o código 200