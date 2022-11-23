#language: pt
#encoding: utf-8
@S002
Funcionalidade: Suite002 - Watson Studio

    Contexto: Acessar plataforma de IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Tecnologia (hm)"
        E se não estiver na home acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud) NOVO"
        Quando acessar os menus "IBM" e "Watson"
        E selecionar o item "Watson Studio"

    @ID-002-002 @CT002 @Smoke
    Cenario: CT002 - Exibir componente
        Então deverá apresentar o título "Gerenciar projetos Watson Studio" na página
        E deverá apresentar os cards com as informações

    @ID-002-003 @CT003
    Cenario: CT003 - Pesquisar componente
        Quando pesquisar um dado "válido"
        Então os resultados apresentados devem conter a palavra pesquisada
        Quando pesquisar um dado "inválido"
        Então a quantidade de resultados deve ser 0

    @ID-002-004 @CT004
    Cenario: CT004 - Filtrar projetos por sigla
        Quando não selecionar uma sigla
        Então deverá mostrar todos os projetos, incluindo os sem sigla
        Quando selecionar uma sigla
        Então deverá mostrar somente os projetos com essa sigla

    @ID-002-006 @CT006
    Cenario: CT006 - Limpar filtro
        Dado que tenha pesquisado por um projeto
        E que tenha pelo menos uma sigla no filtro de siglas
        Quando limpar os filtros
        Então o campo de pesquisa deve estar vazio
        E não deve ter nenhuma sigla selecionada
        E a quantidade de projetos apresentados deve ser alterada
