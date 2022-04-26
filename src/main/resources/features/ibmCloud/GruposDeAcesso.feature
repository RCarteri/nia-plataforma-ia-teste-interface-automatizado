#language: pt
#encoding: utf-8
@S003
Funcionalidade: Suite003 - Grupos de Acesso

	Contexto: Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
		Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Dados do usuário logado carregados com sucesso!"
		Quando acessar a pagina do provedor "IBM Cloud"
		E selecionar o componente "Grupos de Acesso"

	@ID-003-002 @CT002 @Smoke
	Cenario: CT002 - Validar componente
		Então deverá apresentar o título "Lista de Grupos de acesso" na página

	@ID-003-003 @CT003
	Cenario: CT003 - Exibir componente
		Quando existir "Grupo"
		Então deverá apresentar o titulo "Grupos de acesso" no modal
		E deverá apresentar as informações sobre ID e nome
		E deverá mostrar a lista com elementos

	@ID-003-004 @CT004
	Cenario: CT004 - Pesquisar
		Quando pesquisar um dado "válido" no "componente"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar um dado "inválido" no "componente"
		Então deverá apresentar a mensagem "Não há nenhum catálogo com este nome."
		E a quantidade de resultados deve ser 0
		E todas as validações devem retornar sucesso

	@ID-003-005 @CT005 @CT004
	Cenario: CT005 - Pesquisar no modal
		Quando existir "Grupo"
		E pesquisar um dado "válido" no "modal"
		Então deverá apresentar um total de resultados diferente do anterior
		E os resultados apresentados devem conter a palavra pesquisada
		Quando limpar pesquisa
		Então o input deve estar vazio
		E o total de resultados deverá mostrar a quantidade anterior
		Quando pesquisar um dado "inválido" no "modal"
		Então deverá apresentar a mensagem "Nenhum membro encontrado."
		E a quantidade de resultados deve ser 0

	@ID-003-006 @CT006
	Esquema do Cenario: CT006 - Adicionar membro exceção
		Quando existir "Grupo"
		E adicionar membro com os dados  '<chave>'
		Então deverá apresentar a mensagem '<mensagem>' de erro
		Exemplos:
			| chave    | mensagem                                                                                                                                  |
			| F        | Sua chave deve possuir 8 caracteres.                                                                                                      |
			| 22222222 | Sua chave deve começar pela letra "C" ou "F", e em seguida 7 números.Exemplos de chaves válidas: C1234567, F8910111, c1234567, f8910111. |


