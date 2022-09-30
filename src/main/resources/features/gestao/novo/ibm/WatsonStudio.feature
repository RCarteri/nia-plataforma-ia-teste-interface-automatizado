#language: pt
#encoding: utf-8
@S002
Funcionalidade: Suite002 - Watson Studio

    Contexto: Acessar plataforma de IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Tecnologia (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud) NOVO"
        E acessar o menu "IBM"
        E selecionar o item "Watson Studio"

    @ID-002-002 @CT002 @Smoke
    Cenario: CT002 - Exibir componente
        Então deverá apresentar o título "Gerenciar projetos Watson Studio" na página
        E deverá apresentar os cards com as informações