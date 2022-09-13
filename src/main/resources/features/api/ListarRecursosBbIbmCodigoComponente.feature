#language:pt
#encoding: utf-8
@APIS004
Funcionalidade: APISuite004 - Listar recursos BB na IBM Cloud por código do componente

    Contexto: Preparar request
        Dado que defina o endpoint "op5839181v1"

    @ID-API002-001 @APICT001
    Cenario: APICT001 - OK - 200 - Listar buckets do Storage
        Dado que tenha a lista de códigos do componente de "OK-Storage" para ver os detalhes
        Quando enviar um payload "OK-Bucket" com o código do componente
        Então deve retornar o código 200