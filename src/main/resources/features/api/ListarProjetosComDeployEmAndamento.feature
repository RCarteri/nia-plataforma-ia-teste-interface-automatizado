#language:pt
#encoding: utf-8
@APIS001
Funcionalidade: APISuite001 - Listar projetos inteligência artificial com deploy em andamento

    Contexto: Preparar request
        Dado que defina o endpoint "op6586305v1"

    @ID-API001-200 @APICT200
    Cenario: APICT200 - Listar projetos IA com deploy em andamento - OK 200
        Quando enviar um payload "OK"
        Então deve retornar o código 200

    @ID-API001-500 @APICT500
    Cenario: APICT500 - Listar projetos IA com deploy em andamento - Internal Server Error 500
        Quando enviar um payload "Internal Server Error"
        Então deve retornar o código 500