#language: pt
#encoding: utf-8
@S004
Funcionalidade: Suite004 - Informações gerenciais

    @ID-004-011 @CT011
    Cenario: CT011 - Acessar as informações gerenciais
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Tecnologia (hm)"
        E se não estiver na home acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud) NOVO"
        E selecionar o item "Informações gerenciais"
        Então deverão ser apresentados os cards de infromações gerenciais