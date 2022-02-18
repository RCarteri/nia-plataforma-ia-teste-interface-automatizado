#language: pt
#encoding: utf-8
Funcionalidade: Watson Knowledge Catalog
    Contexto: Acessar Watson Knowledge Catalog
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a pagina do provedor IBM Cloud
        E selecionar o componente "Watson Knowledge Catalog"
        Então deverá apresentar o título "Lista de Catálogos" na página
        
    Cenario: Exibir Membros
        Quando exibir "membro"
        Então deverá apresentar o titulo "Membros do catálogo" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá mostrar a lista do "membro"