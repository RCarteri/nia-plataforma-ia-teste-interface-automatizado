#language: pt
#encoding: utf-8
Funcionalidade: Modelos
	Contexto: Acessar Modelos
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Modelos"
		Então deverá apresentar o título "Lista de Modelos" na página

	Cenario: Exibir Modelos
		Quando exibir "modelo"
		Então deverá apresentar o titulo "Detalhes do Modelo" no modal
		E deverá apresentar as informações sobre ID e nome