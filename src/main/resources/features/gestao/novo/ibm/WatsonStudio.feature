#language: pt
#encoding: utf-8
@S007
Funcionalidade: Suite007 - Watson Studio

    Contexto: Acessar plataforma de IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Tecnologia (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud) NOVO"
        E acessar o menu "IBM"
        E selecionar o item "Watson Studio"

    @ID-007-002 @CT002 @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Watson Studio" na página
        E deverá apresentar os cards com as informações