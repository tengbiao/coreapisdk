package ts.coreapi.sdk.model;

public class QueryRepairReqModel {
	/**
	 * 报修单id
	 **/
	private int id;
	/**
	 * 类型
	 **/
	private String type;
	/**
	 * 保修单号
	 **/
	private String repairNo;
	/**
	 * 起始时间
	 **/
	private String startTime;
	/**
	 * 结束时间
	 **/
	private String endTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRepairNo() {
		return repairNo;
	}
	public void setRepairNo(String repairNo) {
		this.repairNo = repairNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
