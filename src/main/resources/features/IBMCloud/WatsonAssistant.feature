#language: pt
#encoding: utf-8
    @WatsonAssistant
Funcionalidade: Watson Assistant
    Contexto: Acessar Watson Assistant
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a pagina do provedor IBM Cloud
        E selecionar o componente "Watson Assistant"
        Então deverá apresentar o título "Lista de Instâncias" na página

    @ExibirComponente
    Cenario: CT005 - Exibir Skills
        Quando exibir "skill"
        Então deverá apresentar o titulo "Skills" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá mostrar a lista do "skills"

    @FiltrarDados
    Cenario: CT008 - Filtrar instâncias
        Quando pesquisar "Watson"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada

    @FiltrarDados
    Cenario: CT009 - Filtrar instâncias sem resultado
        Quando pesquisar "#invalido"
        Então deverá apresentar a mensagem "Não há nenhuma instância com este nome."
        E quantidade de resultados devem ser 0

    @FiltrarDados
    Cenario: CT010 - Limpar filtro
        Quando pesquisar "Watson"
        E limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior