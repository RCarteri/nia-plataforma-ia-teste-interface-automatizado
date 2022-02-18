#language: pt
#encoding: utf-8
Funcionalidade: Watson Studio
	Cenario: Acessar Watson Studio
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Watson Studio"
		Então deverá apresentar o título "Lista de Projetos" na página