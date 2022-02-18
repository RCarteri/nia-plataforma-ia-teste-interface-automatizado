#language: pt
#encoding: utf-8
Funcionalidade: Cloud Object Storage
	Contexto: Acessar Cloud Object Storage
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor IBM Cloud
		E selecionar o componente "Cloud Object Storage"
		Então deverá apresentar o título "Lista de Object Storages" na página

	Cenario: Exibir Storage
		Quando exibir "storage"
		Então deverá apresentar o titulo "Buckets do storage" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista do "bucket"

