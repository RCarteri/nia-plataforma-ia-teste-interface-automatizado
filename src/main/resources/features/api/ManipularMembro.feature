#language:pt
#encoding: utf-8
@APIS008
Funcionalidade: APISuite008 - Manipular acesso aos reursos BB da IBM Cloud

    Contexto: Retornar membros
        Dado que tenha a lista de retorno do "WATSON_STUDIO" no endpoint "op5806077v3"
        E que defina o endpoint "op5839181v1"
        Quando enviar um payload "WATSON_STUDIO-MEMBROS" com o código do componente selecionado
        Mas o papel do usuário logado precisa ser "admin" no projeto selecionado

    @ID-API008-001 @APICT001
    Esquema do Cenario: Cenário: APICT001 - OK - 200
        Mas que selecione um membro da lista que não seja o usuário logado
        Dado que tenha a lista de retorno do "USER_INFO" no endpoint "op5839181v1"
        E que defina o endpoint "op5949338v1"
        Quando enviar um payload "<payload>" com o código do componente selecionado
        Então deve retornar o código 200
        Exemplos:
            | payload                      |
            | WATSON_STUDIO-EDITAR_MEMBRO  |
            | WATSON_STUDIO-REMOVER_MEMBRO |

    @ID-API008-001 @APICT001
    Cenario: Cenário: APICT001 - OK - 200
        Dado que defina o endpoint "op5949338v1"
        E que um dos membros não esteja cadastrado
            | IBMid-310002BCWY | jotharan@bb.com.br          |
            | IBMid-31000098V6 | tsalles@br.ibm.com          |
            | IBMid-663003R55Y | lucas.cusinato@bb.com.br    |
            | IBMid-668000245X | paulo.felcar@bb.com.br      |
            | IBMid-664003MA1X | gabriel.faria@bb.com.br     |
            | IBMid-664003RRWO | fabiana.lauxen@bb.com.br    |
            | IBMid-6640031FD1 | cleunice.fas@bb.com.br      |
            | IBMid-662003O3QU | guilherme.batista@bb.com.br |
            | IBMid-310002ECHN | gustavo.botelho@bb.com.br   |
            | IBMid-665002AY88 | ptaissa@bb.com.br           |
        Quando enviar um payload "WATSON_STUDIO-INCLUIR_MEMBRO" com o código do componente selecionado
        Então deve retornar o código 200
