#language: pt
#encoding: utf-8
@S006L
Funcionalidade: Suite006 - Watson Knowledge Catalog

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a página do provedor "IBM Cloud"
        E selecionar o componente "Watson Knowledge Catalog"

    @ID-006-002L @CT002L @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Catálogos" na página

    @ID-006-003L @CT003L
    Cenario: CT003 - Exibir componente
        Dado que exista "Catálogo"
        Então deverá apresentar o título "Membros do catálogo" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá apresentar o mesmo nome do item selecionado
        E deverá mostrar a lista com elementos

    @ID-006-004L @CT004L
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
            | local      | modal    | mensagem               |
            | componente |          | Nenhum item encontrado |
            | modal      | Catálogo | Nenhum item encontrado |

    @ID-006-005L @CT005L
    Cenario: CT005 - Adicionar membro exceção
        Dado que exista "Catálogo"
        Quando adicionar membro com os dados
            | Chave    | Função |
            |          |        |
            | F        | any    |
            | 22222222 | any    |
        Então deverá apresentar a mensagem de erro de inclusão
            | A chave é um item obrigatório.Papel é obrigatório.  |
            | A chave deve possuir 8 caracteres.                  |
            | A chave deve seguir o padrão: cXXXXXXX, fXXXXXXX... |

    @ID-006-011L @CT011L
    Cenario: CT011 - Editar membro
        Dado que exista "Catálogo" onde o usuário logado seja o administrador
        Quando editar o papel de um membro
        Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Membro editado com sucesso!"

    @ID-006-015L @CT015L
    Cenario: CT015 - Excluir membro
        Dado que exista "Catálogo" onde o usuário logado seja o administrador
        Quando excluir um membro
        Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Membro foi removido com sucesso!"
