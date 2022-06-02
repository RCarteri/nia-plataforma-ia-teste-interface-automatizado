#language: pt
#encoding: utf-8
@S007
Funcionalidade: Suite007 - Watson Studio

    Contexto: Acessar plataforma IA
        Dado que a Plataforma esteja fechada, abra a Plataforma
        E se não estiver logado, realiza o login no Sistema
        Quando acessar a página "Analytics | IA (hm)"
        E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
        E acessar a página do provedor "IBM Cloud"
        E selecionar o componente "Watson Studio"

    @ID-007-002 @CT002 @Smoke
    Cenario: CT002 - Validar componente
        Então deverá apresentar o título "Lista de Projetos" na página

    @ID-007-003 @CT003
    Esquema do Cenario: CT003 - Exibir componente
        Dado que exista "<opção>"
        Então deverá apresentar o titulo "<título>" no modal
        E deverá apresentar as informações sobre ID e nome
        E deverá mostrar a lista com elementos
        Exemplos:
            | opção     | título             |
            | Membros   | Membros do projeto |
            | Notebooks | Notebooks          |

    @ID-007-004 @CT004
    Esquema do Cenario: CT004 - Pesquisar
        Quando pesquisar um dado "válido" no "<local>" "<modal>"
        Então deverá apresentar um total de resultados diferente do anterior
        E os resultados apresentados devem conter a palavra pesquisada
        Quando limpar pesquisa
        Então o input deve estar vazio
        E o total de resultados deverá mostrar a quantidade anterior
        Quando pesquisar um dado "inválido" no "<local>" ""
        Então deverá apresentar a mensagem "<mensagem>"
        E a quantidade de resultados deve ser 0
        E todas as validações devem retornar sucesso
        Exemplos:
            | local      | modal   | mensagem                             |
            | componente |         | Não há nenhum projeto com este nome. |
            | modal      | Membros | Nenhum membro encontrado.            |

    @ID-007-005 @CT005
    Cenario: CT005 - Adicionar membro exceção
        Dado que exista "Membros"
        Quando adicionar membro com os dados
            | Chave    | Função |
            |          |        |
            | F        |        |
            | 22222222 |        |
        Então deverá apresentar a mensagem de erro de inclusão
            | Sua chave é um item obrigatório.É obrigatório escolher um papel.                                                                         |
            | Sua chave deve possuir 8 caracteres.                                                                                                     |
            | Sua chave deve começar pela letra "C" ou "F", e em seguida 7 números.Exemplos de chaves válidas: C1234567, F8910111, c1234567, f8910111. |

    @ID-007-006 @CT006
    Cenario: CT006 - Filtrar projetos por sigla
        Quando selecionar a sigla "NIA"
        Então deverá mostrar a lista de projetos com essa sigla

    @ID-007-007 @CT007
    Cenario: CT007 - Atualizar listagem de projetos
        Quando atualizar a listagem de projetos
        Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Projetos carregados com sucesso!"

    @ID-007-008 @CT008
    Esquema do Cenario: CT008 - Inexistência do componente
        Dado que não exista "<opção>"
        Então deverá ser apresentado o alerta de "informação" com a mensagem "<mensagem>"
        Exemplos:
            | opção       | mensagem                                                       |
            | Notebooks   | Não há nenhum notebook disponível para o projeto atualmente.   |
            | Modelos     | Não há nenhum modelo disponível para o projeto atualmente.     |
            | Data assets | Não há nenhum data asset disponível para o projeto atualmente. |

    @ID-007-011 @CT011
    Cenario: CT011 - Editar membro
        Dado que exista "Membros" onde o usuário logado seja o administrador
        Quando escolher um papel diferente
        Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Membro editado com sucesso!"

    @ID-007-012 @CT012
    Cenario: CT012 - Solicitação de deploy modelo para Triton exception
        Dado que exista "Modelos"
        Quando solicitar deploy modelo para Triton com os dados
            | Nome    | Instância | Notebook | Data Asset |
            |         |           |          |            |
            | a       |           |          |            |
            | @@      |           |          |            |
            | teste a |           |          |            |
        Então deverá apresentar a mensagem de erro de solicitação
            | Nome do modelo é necessário.Nome da instância é necessário.Necessário selecionar um notebook.Necessário selecionar ao menos um data asset. |
            | Nome do modelo deve conter ao menos 2 caracteres.                                                                                          |
            | Nome do modelo não deve conter caracteres especiais e/ou espaços.                                                                          |
            | Nome do modelo não deve conter caracteres especiais e/ou espaços.                                                                          |
