#language:pt
#encoding: utf-8
@APIS002
Funcionalidade: APISuite002 - Listar recursos BB na IBM Cloud

    Contexto: Preparar request
        Dado que defina o endpoint "op5806077v2"

    @ID-API002-001 @APICT001
    Cenario: APICT001 - OK - 200 - Listar instâncias do Watson Assistant
        Quando enviar um payload "OK-Assistant"
        Então deve retornar o código 200

    @ID-API002-001 @APICT001
    Cenario: APICT001 - OK - 200 - Listar modelos do Watson Studio por sigla
        Dado que defina o endpoint "op5806077v3"
        Quando enviar um payload "OK-Sigla"
        Então deve retornar o código 200