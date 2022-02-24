#language: pt
#encoding: utf-8
@GruposDeAcesso
Funcionalidade: Grupos de Acesso
	Contexto: Acessar componente
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Grupos de Acesso"
		Então deverá apresentar o título "Lista de Grupos de acesso" na página

	@ExibirComponente
	Cenario: CT002 - Exibir componente
		Quando exibir "grupo"
		Então deverá apresentar o titulo "GRUPOS DE ACESSO" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista de "grupo"

	@PesquisaComponente
	Cenario: CT003 - Pesquisar componente
		Quando pesquisar "Grupo" no "componente"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar "#invalido" no "componente"
		Então deverá apresentar a mensagem "Não há nenhum catálogo com este nome."
		E a quantidade de resultados deve ser 0

	@PesquisarModalComponente
	Cenario: CT006 - Pesquisar no modal
		Quando exibir "grupo"
		E pesquisar "Adriano" no "modal"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada

	@PesquisarModalComponente
	Cenario: CT007 - Pesquisar no modal sem resultado
		Quando exibir "grupo"
		E pesquisar "#invalido" no "modal"
		Então deverá apresentar a mensagem "Nenhum membro encontrado."
		E a quantidade de resultados deve ser 0

	@PesquisaComponente
	Cenario: CT008 - Limpar pesquisa de componente no modal
		Quando exibir "grupo"
		E pesquisar "Grupo" no "modal"
		E limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
