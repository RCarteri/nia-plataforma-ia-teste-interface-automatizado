#language: pt
#encoding: utf-8
@S001 @Smoke
Funcionalidade: Suite001 - Acessar plataforma IA

    @ID-001-001 @CT001
    Cenario: CT001 - Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Tecnologia (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud) NOVO"
        E a página "Gestão (Cloud) NOVO" deverá ser carregada com sucesso