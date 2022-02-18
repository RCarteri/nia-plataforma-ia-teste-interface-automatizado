package utils;

public enum Razoes {
	CARR_PAG("o carregamento da pagina", 3), CAP_TELA("a captura de tela", 1),
	ENC_SEC("o encerramento desta sessão", 10), LOGIN("confirmação de login", 5),
	TESTE_FTABB("=> FTABB - Testes Automatizados", 0), CARR_PLAT("carregamento da Plataforma", 25);

	private final String razao;
	private final long delay;

	Razoes(String action, int delay) {
		this.razao = action;
		this.delay = delay;
	}

	public String getRazao() {
		return this.razao;
	}

	public long getDelay() {
		return delay;
	}

}
