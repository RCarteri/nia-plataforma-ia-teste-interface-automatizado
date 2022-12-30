#language:pt
#encoding: utf-8
@APIS004
Funcionalidade: APISuite004 - Listar recursos BB na IBM Cloud por código do componente

    @ID-API004-001 @APICT001
    Esquema do Cenário: APICT001 - OK - 200
        Dado que tenha a lista de "dado" "<componente>" no endpoint "<endpoint>"
        E que defina o endpoint "op5839181v1"
        Quando enviar um payload "<payload>" com o código do "componente" selecionado aleatóriamente
        Então deve retornar o código 200
        Exemplos:
            | payload                  | componente               | endpoint    |
            | DATA_ASSET               | WATSON_STUDIO            | op5806077v3 |
            | NOTEBOOKS                | WATSON_STUDIO            | op5806077v3 |
            | WATSON_STUDIO-MEMBROS    | WATSON_STUDIO            | op5806077v3 |
            | WATSON_KNOWLEDGE_CATALOG | WATSON_KNOWLEDGE_CATALOG | op5806077v2 |
            | WATSON_ASSISTANT         | WATSON_ASSISTANT         | op5806077v2 |
            | GRUPOS_DE_ACESSO-MEMBROS | GRUPOS_DE_ACESSO         | op5839181v1 |
            | TESTE_MODELO_TRITON      | TRITON_MODELOS           | op5839181v1 |