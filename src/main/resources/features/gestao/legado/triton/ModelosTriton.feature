#language: pt
#encoding: utf-8
@S008
Funcionalidade: Suite008 - Modelos Triton

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a página do provedor "Triton"
        E selecionar o componente "Modelos Triton"

    @ID-008-002 @CT002 @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Modelos" na página

    @ID-008-003 @CT003
    Cenario: CT003 - Exibir componente
        Dado que exista "Detalhes"
        Então deverá apresentar o título "Detalhes do Modelo Triton" no modal
        E deverá mostrar a lista com detalhes
        E deverá apresentar mais detalhes

    @ID-008-004 @CT004
    Esquema do Cenario: CT004 - Pesquisar
        Quando pesquisar um dado "válido" no "<local>" "<modal>"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada
        Quando limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior
        Quando pesquisar um dado "inválido" no "<local>" ""
        Então deverá apresentar a mensagem "<mensagem>"
        E a quantidade de resultados deve ser 0
        E todas as validações devem retornar sucesso
        Exemplos:
            | local      | modal | mensagem               |
            | componente |       | Nenhum item encontrado |

    @ID-008-009 @CT009
    Cenario: CT009 - Testar modelo Triton
        Dado que exista "Testar Modelo"
        E apresente as informações do request
        Quando executar um request com os dados
            | restaurante |
            | cafe        |
            | torta       |
        Então deverá apresentar um response com sucesso

    @ID-008-014 @CT014
    Cenario: CT014 - Limpar request
        Dado que exista "Testar Modelo"
        E apresente as informações do request
        E o request seja diferente do original
        Quando limpar request
        Então deverá apresentar o request original