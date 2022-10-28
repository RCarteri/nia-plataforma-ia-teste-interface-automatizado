#language:pt
#encoding: utf-8
@APIS002
Funcionalidade: APISuite002 - Listar recursos BB na IBM Cloud

    @ID-API002-001 @APICT001
    Esquema do Cenário: APICT001 - OK - 200
        Dado que defina o endpoint "<endpoint>"
        Quando enviar um payload "<payload>"
        Então deve retornar o código 200
        Exemplos:
            | payload                  | endpoint    |
            | WATSON_STUDIO            | op5806077v3 |
            | WATSON_ASSISTANT         | op5806077v2 |
            | WATSON_KNOWLEDGE_CATALOG | op5806077v2 |
            | CLOUD_OBJECT_STORAGE     | op5806077v2 |
            | MODELOS                  | op5839181v1 |
            | GRUPOS_DE_ACESSO         | op5839181v1 |
            | TRITON_MODELOS           | op5839181v1 |