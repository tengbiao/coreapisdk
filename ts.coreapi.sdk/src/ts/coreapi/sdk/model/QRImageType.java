package ts.coreapi.sdk.model;

public enum QRImageType {
	/**
	 * 客服APP
	 */
	CustomServersApp(5),
	/**
	 * 娱乐场APP
	 */
	CasinoApp(8);
	private int value;

	private QRImageType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
