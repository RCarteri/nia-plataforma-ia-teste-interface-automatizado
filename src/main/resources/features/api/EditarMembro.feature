#language:pt
#encoding: utf-8
@APIS008
Funcionalidade: APISuite008 - Editar acesso aos reursos BB da IBM Cloud
    Contexto: Retornar membros
        Dado que tenha a lista de "projetos" "WATSON_STUDIO" no endpoint "op5806077v3"
        E que defina o endpoint "op5839181v1"
        Quando enviar um payload "WATSON_STUDIO-MEMBROS" com o código do "projeto" selecionado aleatóriamente
        Então salvar os resultados do response

    @ID-API008-001 @APICT001
    Cenário: APICT001 - OK - 200
        Dado que tenha a lista de "membros" "USER_INFO" no endpoint "op5839181v1"
        E que defina o endpoint "op5949338v1"
        Quando enviar um payload "WATSON_STUDIO-EDITAR_MEMBRO" com o código do "membro" selecionado aleatóriamente
        Então deve retornar o código 200
