package ts.coreapi.sdk.model;

import com.google.gson.annotations.SerializedName;

public class ApiResult {
	@SerializedName("Code")
	private int code;
	@SerializedName("Message")
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getIsSuccessCode() {
		return code == ApiResultCode.Success.getCode();
	}
}
