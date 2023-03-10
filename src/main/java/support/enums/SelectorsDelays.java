package support.enums;

public enum SelectorsDelays {
	CIRCLE("bb-spinner", 20),
	LOGIN("#idToken1", 5),
	SPINNER(".spinner", 10),
	MODAL("div.p-dialog-header", 1);

	private final String selector;
	private final long delay;

	SelectorsDelays(String selector, long delay) {
		this.selector = selector;
		this.delay = delay;
	}

	public String getSelector() {
		return selector;
	}

	public long getDelay() {
		return delay;
	}
}
