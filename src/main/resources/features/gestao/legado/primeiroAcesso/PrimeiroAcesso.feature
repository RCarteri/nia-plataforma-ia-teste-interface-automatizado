#language: pt
#encoding: utf-8
@S009L @Smoke
Funcionalidade: Suite009 - Primeiro acesso

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "homologação"
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Primeiro acesso (Cloud)"
        E a página "Primeiro acesso e convite para IBM Cloud" deverá ser carregada com sucesso

    @ID-009-010L @CT010L
    Cenario: CT010 - Cadastrar usuário na IBM Cloud
        Dado que apresente a mensagem "Não encontramos nenhuma conta na IBM Cloud registrada para seu email:"
        Quando seguir tutorial
        Então a página deverá atualizar corretamente
        E o ultimo botão será de finalizar