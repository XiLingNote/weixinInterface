package POJO;

public class WXSetingBean {
	private int weixin_id;
	private String weixin_appid;
	private String weixin_secret;
	
	public int getWeixin_id() {
		return weixin_id;
	}
	public WXSetingBean setWeixin_id(int weixin_id) {
		this.weixin_id = weixin_id;
		return this;
	}
	public String getWeixin_appid() {
		return weixin_appid;
	}
	public WXSetingBean setWeixin_appid(String weixin_appid) {
		this.weixin_appid = weixin_appid;
		return this;
	}
	public String getWeixin_secret() {
		return weixin_secret;
	}
	public WXSetingBean setWeixin_secret(String weixin_secret) {
		this.weixin_secret = weixin_secret;
		return this;
	}	
}
