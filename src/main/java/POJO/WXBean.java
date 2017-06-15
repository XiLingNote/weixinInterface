package POJO;

public class WXBean {
	private String appId;
	private String timestamp;
	private String nonceStr;
	private String signature;
	private String jsapi_ticket;
	
	
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public WXBean setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
		return this;
	}
	public String getAppId() {
		return appId;
	}
	public WXBean setAppId(String appId) {
		this.appId = appId;
		return this;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public WXBean setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public WXBean setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
		return this;
	}
	public String getSignature() {
		return signature;
	}
	public WXBean setSignature(String signature) {
		this.signature = signature;
		return this;
	}
	
	
	
}
