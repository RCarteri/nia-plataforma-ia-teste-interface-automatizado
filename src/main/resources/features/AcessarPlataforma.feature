#language: pt
#encoding: utf-8
@AcessarPlataforma @Smoke
Funcionalidade: Acessar plataforma IA

	Cenario: CT001 - Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema com "chaveF"
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar a tela "Soluções e Serviços de IA" e "Gestão (Cloud)"
		Então verficar se a pagina "Gestão (Cloud)" foi carregada com sucesso
