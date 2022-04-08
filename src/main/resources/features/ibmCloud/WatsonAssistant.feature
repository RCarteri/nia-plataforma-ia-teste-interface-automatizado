#language: pt
#encoding: utf-8
@S005
Funcionalidade: Suite005 - Watson Assistant

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a pagina do provedor "IBM Cloud"
        E selecionar o componente "Watson Assistant"

    @ID-005-002 @CT002 @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Instâncias" na página

    @ID-005-003 @CT003
    Cenario: CT003 - Exibir componente
        Quando existir "Instância"
        Então deverá apresentar o titulo "Skills" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá mostrar a lista com elementos

    @ID-005-004 @CT004
    Cenario: CT004 - Pesquisar
        Quando pesquisar um dado "válido" no "componente"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada
        Quando limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior
        Quando pesquisar um dado "inválido" no "componente"
        Então deverá apresentar a mensagem "Não há nenhuma instância com este nome."
        E a quantidade de resultados deve ser 0
        E todas as validações devem retornar sucesso