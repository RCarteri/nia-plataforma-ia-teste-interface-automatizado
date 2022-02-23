#language: pt
#encoding: utf-8
@WatsonKnowledgeCatalog
Funcionalidade: Watson Knowledge Catalog
    Contexto: Acessar componente
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a pagina do provedor IBM Cloud
        E selecionar o componente "Watson Knowledge Catalog"
        Então deverá apresentar o título "Lista de Catálogos" na página

    @ExibirComponente
    Cenario: CT002 - Exibir componente
        Quando exibir "membro"
        Então deverá apresentar o titulo "Membros do catálogo" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá mostrar a lista do "membro"

    @FiltrarResultadosComponente
    Cenario: CT003 - Pesquisar componente
        Quando pesquisar "Catalog"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada

    @FiltrarResultadosComponente
    Cenario: CT004 - Filtrar componente sem resultado
        Quando pesquisar "#invalido"
        Então deverá apresentar a mensagem "Não há nenhum catálogo com este nome."
        E quantidade de resultados devem ser 0

    @FiltrarResultadosComponente
    Cenario: CT005 - Limpar pesquisa de componente
        Quando pesquisar "Catalog"
        E limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior