#language: pt
#encoding: utf-8
@ModelosTriton
Funcionalidade: Modelos Triton
	Contexto: Acessar componente
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema com "chaveF"
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		E acessar a pagina do provedor "Triton"
		E selecionar o componente "Modelos Triton"

	@Smoke
	Cenario: CT002 - Validar componente
		Então deverá apresentar o título "Lista de Modelos Triton" na página

	@ExibirComponente
	Cenario: CT003 - Exibir componente
		Quando exibir "modelo"
		E escolher "Detalhes"
		Então deverá apresentar o titulo "Detalhes do Modelo Triton" no modal
		E deverá mostrar a lista com detalhes
		E deverá apresentar mais detalhes

	@TestarModeloTriton
	Cenario: CT012 - Testar modelo Triton
		Quando exibir "modelo"
		E escolher "Testar Modelo"
		Então deverá apresentar as informações do request