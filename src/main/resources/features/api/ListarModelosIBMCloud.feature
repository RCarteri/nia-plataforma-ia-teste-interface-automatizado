#language:pt
#encoding: utf-8
@APIS005
Funcionalidade: APISuite005 - Listar modelos na IBM Cloud

    @ID-API005-200 @APICT200
    Cenario: APICT200 - Listar modelos - OK 200
        Dado que tenha a lista de retorno do "WATSON_STUDIO" no endpoint "op5806077v3"
        E que defina o endpoint "op6851522v1"
        Quando enviar um payload "MODELOS" com o código do componente selecionado
        Então deve retornar o código 200