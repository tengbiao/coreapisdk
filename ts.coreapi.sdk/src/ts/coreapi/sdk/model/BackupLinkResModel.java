package ts.coreapi.sdk.model;

import com.google.gson.annotations.SerializedName;

public class BackupLinkResModel {
	/**
	 * id
	 **/
	@SerializedName("Id")
	private int id;
	/**
	 * 图片地址
	 **/
	@SerializedName("ImgUrl")
	private String imgUrl;
	/**
	 * 链接地址
	 **/
	@SerializedName("LinkUrl")
	private String linkUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
