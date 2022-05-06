package support.enums;

public enum TimesAndReasons {
	CARR_PAG("o carregamento da página", 2),
	LOAD_IFRAMES("uma nova tentativa de pesquisa em todos os iframes", 1);

	private final String reason;
	private final long delay;

	TimesAndReasons(String action, long delay) {
		this.reason = action;
		this.delay = delay;
	}

	public String getReason() {
		return this.reason;
	}

	public long getDelay() {
		return delay;
	}
}
