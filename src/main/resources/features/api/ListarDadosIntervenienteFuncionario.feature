#language:pt
#encoding: utf-8
@APIS003
Funcionalidade: APISuite003 - Listar dados interveniente do funcionário

    Contexto: Preparar request
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "desenvolvimento"
        E que não tenha cookies, pegue os cookies
        E que defina o endpoint "dpr/Op5903588-v1"

    @ID-API003-200 @APICT200
    Cenario: APICT200 - Listar siglas acessíveis pelo usuário - OK 200
        Quando definir a chave a ser usada enviando um payload "OK"
        Então deve retornar o código 200