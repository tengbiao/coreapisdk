package ts.coreapi.sdk.model;

public enum BackupLinkType {
	/**
	 * 会员端
	 */
	Member(1),
	/**
	 * 五级端
	 */
	Manage(2);

	private int value;

	BackupLinkType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
