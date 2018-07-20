package ts.coreapi.sdk.model;

public enum RepairType {
	ChangeBrowser(2), NetWorkCheck(3), BackupUrl(4), UpdateFlash(6);

	RepairType(int value) {
		this.value = value;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
