package support.enums;

public enum SelectorsDelays {
	CIRCLE(".p-progress-spinner-circle", 20),
	BLOCKUI(".gaw-blockui.ng-star-inserted", 20),
	LOGIN("#idToken1", 5),
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
