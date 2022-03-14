#language: pt
#encoding: utf-8
@Modelos
Funcionalidade: Modelos

	Contexto: Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor "IBM Cloud"
		E selecionar o componente "Modelos"

	@Smoke
	Cenario: CT002 - Validar componente
		Então deverá apresentar o título "Lista de Modelos" na página

	@ExibirComponente
	Cenario: CT003 - Exibir componente
		Quando exibir "modelo"
		Então deverá apresentar o titulo "Detalhes do Modelo" no modal
		E deverá apresentar as informações sobre ID e nome

	@PesquisaComponente
	Cenario: CT004 - Pesquisar componente
		Quando pesquisar "Zoltar" no "componente"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar "#invalido" no "componente"
		Então deverá apresentar a mensagem "Não há nenhum modelo com este nome."
		E a quantidade de resultados deve ser 0
