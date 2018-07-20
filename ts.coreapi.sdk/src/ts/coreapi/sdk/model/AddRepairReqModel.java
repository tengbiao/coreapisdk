package ts.coreapi.sdk.model;

public class AddRepairReqModel {
	/**
	 * 报修单号
	 **/

	private String repairNo;
	/**
	 * 会员账号
	 **/
	private String accounts;
	/**
	 * 手机号
	 **/
	private String phone;
	/**
	 * 问题类型
	 **/
	private int type;
	/**
	 * 内容
	 **/
	private String content;
	/**
	 * 网址
	 **/
	private String webSite;

	public String getRepairNo() {
		return repairNo;
	}

	public void setRepairNo(String repairNo) {
		this.repairNo = repairNo;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
}
