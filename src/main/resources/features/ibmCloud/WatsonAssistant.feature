#language: pt
#encoding: utf-8
@WatsonAssistant
Funcionalidade: Watson Assistant
    Contexto: Acessar componente
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a pagina do provedor "IBM Cloud"
        E selecionar o componente "Watson Assistant"

    @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Instâncias" na página

    @ExibirComponente
    Cenario: CT003 - Exibir componente
        Quando exibir "instância"
        Então deverá apresentar o titulo "Skills" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá mostrar a lista com elementos

    @PesquisaComponente
    Cenario: CT004 - Pesquisar componente
        Quando pesquisar "Watson" no "componente"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada
        Quando limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior
        Quando pesquisar "#invalido" no "componente"
        Então deverá apresentar a mensagem "Não há nenhuma instância com este nome."
        E a quantidade de resultados deve ser 0