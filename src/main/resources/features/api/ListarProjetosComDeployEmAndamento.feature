#language:pt
#encoding: utf-8
@SAPI001
Funcionalidade: SuiteAPI001 - Listar Projetos Inteligencia Artificial Com Deploy Em Andamento

    Contexto: Preparar request
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "desenvolvimento"
        E que que não tenha cookies, pegue os cookies para o endpoint "/Op6586305-v1"

    @ID-API001-001 @CTAPI001
    Cenario: CTAPI001 - OK - 200
        Quando enviar um payload "OK"
        Então deve retornar o código 200

    @ID-API001-002 @CTAPI002
    Cenario: CTAPI002 - Bad Request - 400
        Quando enviar um payload "Bad Request"
        Então deve retornar o código 400