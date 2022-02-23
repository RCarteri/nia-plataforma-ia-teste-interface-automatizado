#language: pt
#encoding: utf-8
@Modelos
Funcionalidade: Modelos
	Contexto: Acessar Modelos
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Modelos"
		Então deverá apresentar o título "Lista de Modelos" na página

	@ExibirComponente
	Cenario: CT004 - Exibir Modelos
		Quando exibir "modelo"
		Então deverá apresentar o titulo "Detalhes do Modelo" no modal
		E deverá apresentar as informações sobre ID e nome

	@FiltrarResultadosComponente
	Cenario: CT017 - Filtrar instâncias
		Quando pesquisar "Zoltar"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada

	@FiltrarResultadosComponente
	Cenario: CT018 - Filtrar instâncias sem resultado
		Quando pesquisar "#invalido"
		Então deverá apresentar a mensagem "Não há nenhum modelo com este nome."
		E quantidade de resultados devem ser 0

	@FiltrarResultadosComponente
	Cenario: CT019 - Limpar filtro
		Quando pesquisar "Zoltar"
		E limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior