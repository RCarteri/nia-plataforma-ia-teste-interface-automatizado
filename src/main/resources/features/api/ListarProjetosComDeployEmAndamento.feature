#language:pt
#encoding: utf-8
@APIS001
Funcionalidade: APISuite001 - Listar projetos inteligência artificial com deploy em andamento

    Contexto: Preparar request
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "desenvolvimento"
        E que não tenha cookies, pegue os cookies para o endpoint "/Op6586305-v1"

    @ID-API001-001 @APICT001
    Cenario: APICT001 - OK - 200
        Quando enviar um payload "OK"
        Então deve retornar o código 200

    @ID-API001-002 @APICT002
    Cenario: APICT002 - Bad Request - 400
        Quando enviar um payload "Bad Request"
        Então deve retornar o código 400