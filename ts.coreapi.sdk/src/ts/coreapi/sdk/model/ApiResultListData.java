package ts.coreapi.sdk.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiResultListData<T> extends ApiResult{
	@SerializedName("Data")
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
