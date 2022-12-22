#language:pt
#encoding: utf-8
@APIS004
Funcionalidade: APISuite004 - Listar recursos BB na IBM Cloud por código do componente

    @ID-API004-001 @APICT001
    Esquema do Cenário: APICT001 - OK - 200
        Dado que defina o endpoint "op5839181v1"
        E que tenha a lista de dados do componente "<componente>" no endpoint "<endpoint>"
        Quando enviar um payload "<payload>" com o código da lista de dados do componente "<componente>"
        Então deve retornar o código 200
        Exemplos:
            | payload                  | componente               | endpoint    |
            | DATA_ASSET               | WATSON_STUDIO            | op5806077v3 |
            | NOTEBOOKS                | WATSON_STUDIO            | op5806077v3 |
            | WATSON_STUDIO_MEMBROS    | WATSON_STUDIO            | op5806077v3 |
            | WATSON_KNOWLEDGE_CATALOG | WATSON_KNOWLEDGE_CATALOG | op5806077v2 |
            | WATSON_ASSISTANT         | WATSON_ASSISTANT         | op5806077v2 |
            | GRUPOS_DE_ACESSO_MEMBROS | GRUPOS_DE_ACESSO         | op5839181v1 |
            | TESTE_MODELO_TRITON      | TRITON_MODELOS           | op5839181v1 |