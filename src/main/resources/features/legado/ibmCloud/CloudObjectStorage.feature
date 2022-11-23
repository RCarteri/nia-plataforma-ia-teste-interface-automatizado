#language: pt
#encoding: utf-8
@S002L
Funcionalidade: Suite002 - Cloud Object Storage

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a página do provedor "IBM Cloud"
        E selecionar o componente "Cloud Object Storage"

    @ID-002-002L @CT002L @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Object Storages" na página

    @ID-002-003L @CT003L
    Cenario: CT003 - Exibir componente
        Dado que exista "Bucket"
        Então deverá apresentar o título "Buckets do storage" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá apresentar o mesmo nome do item selecionado
        E deverá mostrar a lista com elementos

    @ID-002-004L @CT004L
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

    @ID-002-008L @CT008L
    Cenario: CT008 - Inexistência do componente
        Dado que não exista "Bucket"
        Então deverá ser apresentado o alerta de "informação" com a mensagem "Não há nenhum bucket disponível para o projeto atualmente."