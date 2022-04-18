#language: pt
#encoding: utf-8
@S009 @Smoke
Funcionalidade: Suite009 - Primeiro acesso

	Contexto: Acessar plataforma IA
		Dado que a Plataforma esteja fechada, abra a Plataforma
		E se não estiver logado, realiza o login no Sistema
		Quando acessar a pagina "Analytics | IA (hm)"
		E acessar o menu "Soluções e Serviços de IA" e "Primeiro acesso (Cloud)"
		Então verficar se a pagina "Primeiro acesso e convite para IBM Cloud" foi carregada com sucesso

	@ID-009-011 @CT011
	Cenario: CT011 - Cadastrar usuário na IBM Cloud
		Dado que apresente a mensagem "Não encontramos nenhuma conta na IBM Cloud registrada para seu email:"
		Quando seguir tutorial
		Então a página deverá atualizar corretamente
		E o ultimo botão será de finalizar


