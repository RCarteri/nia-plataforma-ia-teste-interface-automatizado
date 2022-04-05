#language: pt
#encoding: utf-8
@S002
Funcionalidade: Suite002 - Cloud Object Storage

	Contexto: Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor "IBM Cloud"
		E selecionar o componente "Cloud Object Storage"

	@ID-002-002 @CT002 @Smoke
	Cenario: CT002 - Validar componente
		Então deverá apresentar o título "Lista de Object Storages" na página

	@ID-002-003 @CT003
	Cenario: CT003 - Exibir componente
		Quando exibir "storage"
		Então deverá apresentar o titulo "Buckets do storage" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista com elementos

	@ID-002-004 @CT004
	Cenario: CT004 - Pesquisar componente
		Quando pesquisar "Minhas Financas" no "componente"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar "#invalido" no "componente"
		Então deverá apresentar a mensagem "Não há nenhum storage com este nome."
		E a quantidade de resultados deve ser 0

	@ID-002-009 @CT009
	Cenario: CT009 - Validar mensagem de alerta de inexistência de opção no projeto
		Quando não existir 'Bucket'
		Então deverá apresentar a mensagem de alerta 'Não há nenhum bucket disponível para o projeto atualmente.'