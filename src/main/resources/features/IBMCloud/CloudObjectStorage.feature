#language: pt
#encoding: utf-8
@CloudObjectStorage
Funcionalidade: Cloud Object Storage
	Contexto: Acessar componente
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Cloud Object Storage"
		Então deverá apresentar o título "Lista de Object Storages" na página

	@ExibirComponente
	Cenario: CT002 - Exibir componente
		Quando exibir "storage"
		Então deverá apresentar o titulo "Buckets do storage" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista do "bucket"

	@PesquisaComponente
	Cenario: CT003 - Pesquisar componente
		Quando pesquisar "Minhas Financas" no "componente"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada

	@PesquisaComponente
	Cenario: CT004 - Filtrar componente sem resultado
		Quando pesquisar "#invalido" no "componente"
		Então deverá apresentar a mensagem "Não há nenhum storage com este nome."
		E quantidade de resultados devem ser 0

	@PesquisaComponente
	Cenario: CT005 - Limpar pesquisa de componente
		Quando pesquisar "Minhas Financas" no "componente"
		E limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior