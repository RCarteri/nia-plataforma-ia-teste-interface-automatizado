package support.enums;

public enum SelectorsDelays {
	CARR_PAG(".p-progress-spinner-circle", 7),
	LOGIN("#idToken1", 5);

	private final String selector;
	private final long delay;

	SelectorsDelays(String selector, long delay) {
		this.selector = selector;
		this.delay = delay;
	}

	public String getSelector() {
		return this.selector;
	}

	public long getDelay() {
		return delay;
	}
}
