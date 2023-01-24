#language:pt
#encoding: utf-8
@APIS007
Funcionalidade: APISuite007 - Listar a frequência de deploy agrupado por ano-mes, modelo de IA e projeto de IA

    Contexto: Preparar request
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "desenvolvimento"
        E que não tenha cookies, pegue os cookies
        E que defina o endpoint "nia/Op7045172-v2"

    @ID-API007-200 @APICT200
    Cenário: APICT200 - OK 200
        Quando enviar um payload "OK"
        Então deve retornar o código 200
