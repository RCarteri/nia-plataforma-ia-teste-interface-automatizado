#language: pt
#encoding: utf-8
@S007
Funcionalidade: Suite007 - Watson Studio

  Contexto: Acessar plataforma IA
    Dado que a Plataforma esteja fechada, abra a Plataforma
    E se não estiver logado, realiza o login no Sistema
    Quando acessar a pagina "Analytics | IA (hm)"
    E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
    E acessar a pagina do provedor "IBM Cloud"
    E selecionar o componente "Watson Studio"

  @ID-007-002 @CT002 @Smoke
  Cenario: CT002 - Validar componente
    Então deverá apresentar o título "Lista de Projetos" na página

  @ID-007-003 @CT003
  Esquema do Cenario: CT003 - Exibir componente
    Quando existir "<opção>"
    Então deverá apresentar o titulo "<título>" no modal
    E deverá apresentar as informações sobre ID e nome
    E deverá mostrar a lista com elementos
    Exemplos:
      | opção     | título             |
      | Membros   | Membros do projeto |
      | Notebooks | Notebooks          |

  @ID-007-004 @CT004
  Cenario: CT004 - Pesquisar
    Quando pesquisar um dado "válido" no "componente"
    Então deverá apresentar um total de resultados diferente do anterior
    E os resultados apresentados devem conter a palavra pesquisada
    Quando limpar pesquisa
    Então o input deve estar vazio
    E o total de resultados deverá mostrar a quantidade anterior
    Quando pesquisar um dado "inválido" no "componente"
    Então deverá apresentar a mensagem "Não há nenhum projeto com este nome."
    E a quantidade de resultados deve ser 0
    E todas as validações devem retornar sucesso

  @ID-007-005 @CT005 @CT004
  Cenario: CT005 - Pesquisar no modal
    Quando existir "Membros"
    E pesquisar um dado "válido" no "modal"
    Então deverá apresentar um total de resultados diferente do anterior
    E os resultados apresentados devem conter a palavra pesquisada
    Quando limpar pesquisa
    Então o input deve estar vazio
    E o total de resultados deverá mostrar a quantidade anterior
    Quando pesquisar um dado "inválido" no "modal"
    Então deverá apresentar a mensagem "Nenhum membro encontrado."
    E a quantidade de resultados deve ser 0
    E todas as validações devem retornar sucesso

  @ID-007-006 @CT006
  Esquema do Cenario: CT006 - Adicionar membro exceção
    Quando existir "Membros"
    E adicionar membro com os dados <função> '<chave>'
    Então deverá apresentar a mensagem '<mensagem>' de erro
    Exemplos:
      | chave    | função        | mensagem                                                                                                                                 |
      |          |               | Sua chave é um item obrigatório.É obrigatório escolher um papel.                                                                         |
      | F        | Administrador | Sua chave deve possuir 8 caracteres.                                                                                                     |
      | 22222222 | Administrador | Sua chave deve começar pela letra "C" ou "F", e em seguida 7 números.Exemplos de chaves válidas: C1234567, F8910111, c1234567, f8910111. |

  @ID-007-007 @CT007
  Cenario: CT007 - Filtrar projetos por sigla
    Quando selecionar a sigla "NIA"
    Então deverá mostrar a lista de projetos com essa sigla

  @ID-007-008 @CT008
  Cenario: CT008 - Atualizar listagem de projetos
    Quando atualizar a listagem de projetos
    Então deverá apresentar a mensagem de alerta "Carregamento dos projetos em andamento!"

  @ID-007-009 @CT009
  Esquema do Cenario: CT009 - Inexistência do componente
    Quando não existir "<opção>"
    Então deverá apresentar a mensagem de alerta "<mensagem>"
    Exemplos:
      | opção       | mensagem                                                       |
      | Notebooks   | Não há nenhum notebook disponível para o projeto atualmente.   |
      | Modelos     | Não há nenhum modelo disponível para o projeto atualmente.     |
      | Data assets | Não há nenhum data asset disponível para o projeto atualmente. |