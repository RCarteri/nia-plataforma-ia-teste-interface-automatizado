#language:pt
#encoding: utf-8
@APIS004
Funcionalidade: APISuite004 - Listar recursos BB na IBM Cloud por código do componente

    @ID-API004-001 @APICT001
    Esquema do Cenário: APICT001 - OK - 200
        Dado que defina o endpoint "op5839181v1"
        E que tenha a lista de códigos do componente "<componente>" para ver os detalhes
        Quando enviar um payload "<payload>" com o código e nome do componente "<componente>"
        Então deve retornar o código 200
        Exemplos:
            | componente               | payload                      |
            | WATSON_KNOWLEDGE_CATALOG | WATSON_KNOWLEDGE_CATALOG     |
            | CLOUD_OBJECT_STORAGE     | CLOUD_OBJECT_STORAGE_BUCKETS |
            | WATSON_ASSISTANT         | WATSON_ASSISTANT             |
            | WATSON_STUDIO            | WATSON_SPACE                 |
