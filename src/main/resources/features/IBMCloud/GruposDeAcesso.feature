#language: pt
#encoding: utf-8
Funcionalidade: Grupos de Acesso
	Contexto: Acessar Grupos de Acesso
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Grupos de Acesso"
		Então deverá apresentar o título "Lista de Grupos de acesso" na página

	Cenario: Exibir Grupos de Acesso
		Quando exibir "grupo"
		Então deverá apresentar o titulo "GRUPOS DE ACESSO" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista do "grupo"
