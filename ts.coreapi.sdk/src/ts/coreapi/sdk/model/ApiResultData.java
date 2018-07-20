package ts.coreapi.sdk.model;

import com.google.gson.annotations.SerializedName;

public class ApiResultData<T> extends ApiResult {
	@SerializedName("Data")
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
