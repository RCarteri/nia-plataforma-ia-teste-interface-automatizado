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

	@ID-008-010 @CT010
	Cenario: CT010 - Testar modelo Triton
		Quando existir "Testar Modelo"
		Então deverá apresentar as informações do request