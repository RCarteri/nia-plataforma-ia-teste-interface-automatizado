#language:pt
#encoding: utf-8
Funcionalidade: Listar Projetos Inteligencia Artificial Com Deploy Em Andamento

    Cenario: GET200
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login em "desenvolvimento"
        E que que não tenha cookies, pegue os cookies
        Quando enviar o payload para o endpoint "/Op6586305-v1"
            | Chave          | Valor |
            | nomeComponente | 1     |