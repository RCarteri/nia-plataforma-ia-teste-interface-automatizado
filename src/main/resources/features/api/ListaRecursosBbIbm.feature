#language:pt
#encoding: utf-8
@APIS002
Funcionalidade: APISuite002 - Listar recursos BB na IBM Cloud

    Contexto: Preparar request
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "desenvolvimento"
        E que não tenha cookies, pegue os cookies para o endpoint "/Op5806077-v3"

    @ID-API002-001 @APICT001
    Cenario: APICT001 - OK - 200 - Listar modelos Watson Studio por sigla
        Quando enviar um payload "OK"
        Então deve retornar o código 200