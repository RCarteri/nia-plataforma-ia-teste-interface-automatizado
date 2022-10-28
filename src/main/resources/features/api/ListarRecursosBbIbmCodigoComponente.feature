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
      | componente               | endpoint    | payload                      |
      | WATSON_KNOWLEDGE_CATALOG | op5806077v2 | WATSON_KNOWLEDGE_CATALOG     |
      | CLOUD_OBJECT_STORAGE     | op5806077v2 | CLOUD_OBJECT_STORAGE_BUCKETS |
      | WATSON_ASSISTANT         | op5806077v2 | WATSON_ASSISTANT             |
      | GRUPOS_DE_ACESSO         | op5839181v1 | GRUPOS_DE_ACESSO_MEMBROS     |