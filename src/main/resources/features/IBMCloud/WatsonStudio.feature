#language: pt
#encoding: utf-8
@WatsonStudio
Funcionalidade: Watson Studio

	Contexto: Acessar componente
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Watson Studio"
		Então deverá apresentar o título "Lista de Projetos" na página

	@ExibirComponente
	Cenario: CT002 - Exibir componente
		Quando exibir "projeto"
		E escolher "Membros"
		Então deverá apresentar o titulo "Membros do projeto" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista com elementos

	@PesquisaComponente
	Cenario: CT003 - Pesquisar componente
		Quando pesquisar "Teste" no "componente"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar "#invalido" no "componente"
		Então deverá apresentar a mensagem "Não há nenhum projeto com este nome."
		E a quantidade de resultados deve ser 0

	@PesquisaModalComponente
	Cenario: CT004 - Pesquisar no modal
		Quando exibir "projeto"
		E escolher "Membros"
		E pesquisar "Bruno" no "modal"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar "#invalido" no "modal"
		Então deverá apresentar a mensagem "Nenhum membro encontrado."
		E a quantidade de resultados deve ser 0

	@AdicionarMembroException
	Esquema do Cenario: CT005 - Adicionar membro exceção
		Quando exibir "projeto"
		E escolher "Membros"
		E adicionar membro com os dados <função> '<chave>'
		Então deverá apresentar a mensagem '<mensagem>' de erro
		Exemplos:
			| chave    | função        | mensagem                                                                                                                                 |
			|          |               | Sua chave é um item obrigatório.É obrigatório escolher um papel.                                                                         |
			| F        | Administrador | Sua chave deve possuir 8 caracteres.                                                                                                     |
			| 22222222 | Administrador | Sua chave deve começar pela letra "C" ou "F", e em seguida 7 números.Exemplos de chaves válidas: C1234567, F8910111, c1234567, f8910111. |

	@FiltroSigla
	Cenario: CT006 - Filtrar projetos por sigla
		Quando selecionar a sigla "NIA"
		Então deverá mostrar a lista de projetos com essa sigla

	@AtualizarProjetos
	Cenario: CT007 - Atualizar listagem de projetos
		Quando atualizar a listagem de projetos
		Então deverá apresentar a mensagem de alerta "Carregamento dos projetos em andamento!"

	@Notebook
	Cenario: CT008 - Validar mensagem inexistência de notebooks no projeto
		Quando exibir "projeto"
		E escolher "Notebooks"
		Mas não existir notebook
		Então deverá apresentar a mensagem de alerta "Não há nenhum notebook disponível para o projeto atualmente."

	@Notebook
	Cenario: CT009 - Exibir notebook
		Quando exibir "projeto"
		E escolher "Notebooks"
		E existir notebook
		Então deverá apresentar o titulo "Notebooks" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista com elementos
