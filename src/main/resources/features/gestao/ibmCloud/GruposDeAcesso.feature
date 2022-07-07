#language: pt
#encoding: utf-8
@S003
Funcionalidade: Suite003 - Grupos de Acesso

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a página do provedor "IBM Cloud"
        E selecionar o componente "Grupos de Acesso"

    @ID-003-002 @CT002 @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Grupos de acesso" na página

    @ID-003-003 @CT003
    Cenario: CT003 - Exibir componente
        Dado que exista "Grupo"
        Então deverá apresentar o titulo "Grupos de acesso" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá apresentar o mesmo nome do item selecionado
        E deverá mostrar a lista com elementos

    @ID-003-004 @CT004
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
            | modal      | Grupo | Nenhum item encontrado |

    @ID-003-005 @CT005
    Cenario: CT005 - Adicionar membro exceção
        Dado que exista "Grupo"
        Quando adicionar membro com os dados
            | Chave    | Função |
            |          |        |
            | F        |        |
            | 22222222 |        |
        Então deverá apresentar a mensagem de erro de inclusão
            | A chave é um item obrigatório.                      |
            | A chave deve possuir 8 caracteres.                  |
            | A chave deve seguir o padrão: cXXXXXXX, fXXXXXXX... |