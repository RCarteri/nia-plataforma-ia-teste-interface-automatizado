#language:pt
#encoding: utf-8
@APIS006
Funcionalidade: APISuite006 - Listar projetos de inteligência artificial

    @ID-API006-200 @APICT200
    Cenario: APICT200 - Listar projetos de IA - OK 200
        Dado que defina o endpoint "op6583452v1"
        Quando enviar um payload "PROJETOS"
        Então deve retornar o código 200