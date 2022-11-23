#language: pt
#encoding: utf-8
@S003
Funcionalidade: Suite003 - Acessar os indicadores

    @ID-003-010 @CT010
    Cenario: CT010 - Acessar os indicadores
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Tecnologia (hm)"
        E se não estiver na home acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud) NOVO"
        E selecionar o item "Indicadores"
        Então deverão ser apresentados os gráficos na tela