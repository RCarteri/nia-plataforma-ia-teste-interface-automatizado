#language:pt
#encoding: utf-8
@APIS007
Funcionalidade: APISuite007 - Listar a frequência de deploy agrupado por ano-mes, modelo de IA e projeto de IA

    @ID-API007-001 @APICT001
    Cenário: APICT001 - OK - 200
        Dado que defina o endpoint "op7045172v1"
        Quando enviar um payload "OK"
        Então deve retornar o código 200
