#language: pt
#encoding: utf-8
@S002
Funcionalidade: Suite002 - Cloud Object Storage

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Dados do usuário logado carregados com sucesso!"
        Quando acessar a pagina do provedor "IBM Cloud"
        E selecionar o componente "Cloud Object Storage"

    @ID-002-002 @CT002 @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Object Storages" na página

    @ID-002-003 @CT003
    Cenario: CT003 - Exibir componente
        Quando existir "Bucket"
        Então deverá apresentar o titulo "Buckets do storage" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá apresentar o mesmo nome do item selecionado
        E deverá mostrar a lista com elementos

    @ID-002-004 @CT004
    Esquema do Cenario: Cenario: CT004 - Pesquisar
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
            | local      | modal | mensagem                             |
            | componente |       | Não há nenhum storage com este nome. |

    @ID-002-009 @CT009
    Cenario: CT009 - Inexistência do componente
        Quando não existir "Bucket"
        Então deverá ser apresentado o alerta de "informação" com a mensagem "Não há nenhum bucket disponível para o projeto atualmente."