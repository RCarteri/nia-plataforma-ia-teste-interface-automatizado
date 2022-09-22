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
            | componente   | payload   |
            | OK-Storage   | OK-Bucket |
            | OK-Catalog   | OK-Membro |
            | OK-Assistant | OK-Skill  |
            | OK-Studio    | OK-Membro |
