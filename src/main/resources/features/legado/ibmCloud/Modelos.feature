#language: pt
#encoding: utf-8
@S004L
Funcionalidade: Suite004 - Modelos

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a página do provedor "IBM Cloud"
        E selecionar o componente "Modelos"

    @ID-004-002L @CT002L @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Modelos" na página

    @ID-004-003L @CT003L
    Cenario: CT003 - Exibir componente
        Dado que exista "Modelo"
        Então deverá apresentar o título "Detalhes do Modelo" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá apresentar o mesmo nome do item selecionado

    @ID-004-004L @CT004L
    Esquema do Cenario: CT004 - Pesquisar
        Quando pesquisar um dado "válido" no "<local>" "<modal>"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada
        Quando limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior
        Quando pesquisar um dado "inválido" no "componente" ""
        Então deverá apresentar a mensagem "<mensagem>"
        E a quantidade de resultados deve ser 0
        E todas as validações devem retornar sucesso
        Exemplos:
            | local      | modal | mensagem               |
            | componente |       | Nenhum item encontrado |