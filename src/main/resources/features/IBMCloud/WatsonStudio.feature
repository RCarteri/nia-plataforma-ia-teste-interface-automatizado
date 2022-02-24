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
		E deverá mostrar a lista de "membro"

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
