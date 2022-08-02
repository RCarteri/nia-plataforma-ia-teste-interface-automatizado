#language:pt
#encoding: utf-8
@APIS001
Funcionalidade: APISuite001 - Listar Projetos Inteligencia Artificial Com Deploy Em Andamento

    Contexto: Preparar request
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "desenvolvimento"
        E que que não tenha cookies, pegue os cookies para o endpoint "/Op6586305-v1"

    @ID-API001-001 @APICT001
    Cenario: APICT001 - OK - 200
        Quando enviar um payload "OK"
        Então deve retornar o código 200