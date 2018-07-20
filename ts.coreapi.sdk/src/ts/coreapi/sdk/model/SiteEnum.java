package ts.coreapi.sdk.model;

public enum SiteEnum {

	TS111(26), TS666(35), CQ11(17), TS777(21), TS5588(2), TS77(5);
	private int site;

	private SiteEnum(int site) {
		this.site = site;
	}

	public int getSite() {
		return this.site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public static SiteEnum getSiteEnum(int site) {
		switch (site) {
		case 26:
			return SiteEnum.TS111;
		case 35:
			return SiteEnum.TS666;
		case 17:
			return SiteEnum.CQ11;
		case 21:
			return SiteEnum.TS777;
		case 2:
			return SiteEnum.TS5588;
		case 5:
			return SiteEnum.TS77;
		}
		return SiteEnum.TS111;
	}
}
