#language: pt
#encoding: utf-8
@S001 @Smoke
Funcionalidade: Suite001 - Acessar plataforma IA

	@ID-001-001 @CT001
	Cenario: CT001 - Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar o menu "Soluções e Serviços de IA" e "Gestão (Cloud)"
		Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Dados do usuário logado carregados com sucesso!"
		E a pagina "Gestão (Cloud)" deverá ser carregada com sucesso
