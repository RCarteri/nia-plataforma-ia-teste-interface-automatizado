#language: pt
#encoding: utf-8
@S008
Funcionalidade: Suite008 - Modelos Triton

	Contexto: Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor "Triton"
		E selecionar o componente "Modelos Triton"

	@ID-008-002 @CT002 @Smoke
	Cenario: CT002 - Validar componente
		Então deverá apresentar o título "Lista de Modelos" na página

	@ID-008-003 @CT003
	Cenario: CT003 - Exibir componente
		Quando existir "Detalhes"
		Então deverá apresentar o titulo "Detalhes do Modelo Triton" no modal
		E deverá mostrar a lista com detalhes
		E deverá apresentar mais detalhes

	@ID-008-004 @CT004
	Cenario: CT004 - Pesquisar componente
		Quando pesquisar um dado "válido" no "componente"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar um dado "inválido" no "componente"
		Então deverá apresentar a mensagem "Não há nenhum modelo com este nome."
		E a quantidade de resultados deve ser 0
		E todas as validações devem retornar sucesso

	@ID-008-010 @CT010
	Cenario: CT010 - Testar modelo Triton
		Quando existir "Testar Modelo"
		Então deverá apresentar as informações do request