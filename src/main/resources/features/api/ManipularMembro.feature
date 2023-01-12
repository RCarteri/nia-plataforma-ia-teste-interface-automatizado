#language:pt
#encoding: utf-8
@APIS008
Funcionalidade: APISuite008 - Manipular acesso aos reursos BB da IBM Cloud

    Contexto: Retornar membros
        Dado que tenha a lista de retorno do "WATSON_STUDIO" no endpoint "op5806077v3"
        E que defina o endpoint "op5839181v1"
        Quando enviar um payload "WATSON_STUDIO-MEMBROS" com o código do componente selecionado
        Mas o papel do usuário logado precisa ser "admin" no projeto selecionado
        E que selecione um membro da lista que não seja o usuário logado
        Dado que tenha a lista de retorno do "USER_INFO" no endpoint "op5839181v1"
        E que defina o endpoint "op5949338v1"

    @ID-API008-001 @APICT001
    Esquema do Cenario: Cenário: APICT001 - OK - 200
        Quando enviar um payload "<payload>" com o código do componente selecionado
        Então deve retornar o código 200
        Exemplos:
            | payload                      |
            | WATSON_STUDIO-EDITAR_MEMBRO  |
            | WATSON_STUDIO-REMOVER_MEMBRO |
