#language: pt
#encoding: utf-8
@S001 @Smoke
Funcionalidade: Suite001 - Acessar plataforma IA

	@ID-001-001 @CT001
	Cenario: CT001 - Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		Então verficar se a pagina "Gestão (Cloud)" foi carregada com sucesso
