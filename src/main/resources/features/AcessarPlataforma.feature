#language: pt
#encoding: utf-8
Funcionalidade: Priemiro acesso
    @AcessarPlataforma @Smoke
    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema com "chaveF"
        Quando acessar a pagina "Analytics | IA (hm)"
        E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a pagina do provedor "Triton"
        E selecionar o componente "Modelos Triton"
