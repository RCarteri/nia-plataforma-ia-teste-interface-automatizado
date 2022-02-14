package utils;

public enum Razoes {
	CARR_PAG("o carregamento da pagina", 1), CAP_TELA("a captura de tela", 2),
	ENC_SEC("o encerramento desta sessão", 1), LOGIN("confirmação de login", 2),
	TESTE_FTABB("=> FTABB - Testes Automatizados", 0), CARR_PLAT("carregamento da Plataforma", 1);

	private String razao;
	private long delay;

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
