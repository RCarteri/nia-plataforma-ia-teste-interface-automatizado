#language: pt
#encoding: utf-8
@WatsonKnowledgeCatalog
Funcionalidade: Watson Knowledge Catalog
    Contexto: Acessar componente
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema com "chaveF"
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a pagina do provedor "IBM Cloud"
        E selecionar o componente "Watson Knowledge Catalog"

    @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Catálogos" na página

    @ExibirComponente
    Cenario: CT003 - Exibir componente
        Quando exibir "catálogo"
        Então deverá apresentar o titulo "Membros do catálogo" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá mostrar a lista com elementos

    @PesquisaComponente
    Cenario: CT004 - Pesquisar componente
        Quando pesquisar "Catalog" no "componente"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada
        Quando limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior
        Quando pesquisar "#invalido" no "componente"
        Então deverá apresentar a mensagem "Não há nenhum catálogo com este nome."
        E a quantidade de resultados deve ser 0

    @PesquisaModalComponente
    Cenario: CT005 - Pesquisar no modal
        Quando exibir "catálogo"
        E pesquisar "Bruno" no "modal"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada
        Quando limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior
        Quando pesquisar "#invalido" no "modal"
        Então deverá apresentar a mensagem "Nenhum membro encontrado."
        E a quantidade de resultados deve ser 0

    @AdicionarMembroException
    Esquema do Cenario: CT006 - Adicionar membro exceção
        Quando exibir "catálogo"
        E adicionar membro com os dados <função> '<chave>'
        Então deverá apresentar a mensagem '<mensagem>' de erro
        Exemplos:
            | chave    | função        | mensagem                                                                                                                                 |
            |          |               | Sua chave é um item obrigatório.É obrigatório escolher um papel.                                                                                                      |
            | F        | Administrador | Sua chave deve possuir 8 caracteres.                                                                                                     |
            | 22222222 | Administrador | Sua chave deve começar pela letra "C" ou "F", e em seguida 7 números.Exemplos de chaves válidas: C1234567, F8910111, c1234567, f8910111. |
