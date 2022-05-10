#language: pt
#encoding: utf-8
@S006
Funcionalidade: Suite006 - Watson Knowledge Catalog

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Dados do usuário logado carregados com sucesso!"
        Quando acessar a página do provedor "IBM Cloud"
        E selecionar o componente "Watson Knowledge Catalog"

    @ID-006-002 @CT002 @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Catálogos" na página

    @ID-006-003 @CT003
    Cenario: CT003 - Exibir componente
        Quando existir "Catálogo"
        Então deverá apresentar o titulo "Membros do catálogo" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá apresentar o mesmo nome do item selecionado
        E deverá mostrar a lista com elementos

    @ID-006-004 @CT004
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
            | local      | modal    | mensagem                              |
            | componente |          | Não há nenhum catálogo com este nome. |
            | modal      | Catálogo | Nenhum membro encontrado.             |

    @ID-006-005 @CT005
    Esquema do Cenario: CT005 - Adicionar membro exceção
        Quando existir "Catálogo"
        E adicionar membro com os dados <função> '<chave>'
        Então deverá apresentar a mensagem '<mensagem>' de erro
        Exemplos:
            | chave    | função        | mensagem                                                                                                                                 |
            |          |               | Sua chave é um item obrigatório.É obrigatório escolher um papel.                                                                         |
            | F        | Administrador | Sua chave deve possuir 8 caracteres.                                                                                                     |
            | 22222222 | Administrador | Sua chave deve começar pela letra "C" ou "F", e em seguida 7 números.Exemplos de chaves válidas: C1234567, F8910111, c1234567, f8910111. |