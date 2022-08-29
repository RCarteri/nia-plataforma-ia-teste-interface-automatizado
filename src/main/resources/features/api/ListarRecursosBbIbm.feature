#language:pt
#encoding: utf-8
@APIS002
Funcionalidade: APISuite002 - Listar recursos BB na IBM Cloud

    Contexto: Preparar request
        Dado que defina o endpoint "op5806077v3"

    @ID-API002-001 @APICT001
    Cenario: APICT001 - OK - 200 - Listar modelos Watson Studio por sigla
        Quando enviar um payload "OK"
        Então deve retornar o código 200