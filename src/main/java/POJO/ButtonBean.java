package POJO;

import java.util.List;

public class ButtonBean {
	
	List<ButtonBean> button;
	List<ButtonBean> subButton;
	String sub_button;	//��	�����˵����飬����ӦΪ1~5��
	String type;	//��	�˵�����Ӧ�������ͣ�view��ʾ��ҳ���ͣ�click��ʾ������ͣ�miniprogram��ʾС��������
	String name;	//��	�˵����⣬������16���ֽڣ��Ӳ˵�������60���ֽ�
	String key;	//click�ȵ�����ͱ���	�˵�KEYֵ��������Ϣ�ӿ����ͣ�������128�ֽ�
	String url;	//view��miniprogram���ͱ���	��ҳ���ӣ��û�����˵��ɴ����ӣ�������1024�ֽڡ�typeΪminiprogramʱ����֧��С������ϰ汾�ͻ��˽��򿪱�url��
	String media_id;	//media_id���ͺ�view_limited���ͱ���	�������������زĽӿڷ��صĺϷ�media_id
	String appid;
	String miniprogram;//���ͱ���	//С�����appid������֤���ں�String agepath
	String pagepath;
	public String getPagepath() {
		return pagepath;
	}
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	public ButtonBean() {
		super();
	}
	public List<ButtonBean> getButton() {
		return button;
	}
	public void setButton(List<ButtonBean> button) {
		this.button = button;
	}
	
	public List<ButtonBean> getSubButton() {
		return subButton;
	}
	public void setSubButton(List<ButtonBean> subButton) {
		this.subButton = subButton;
	}
	public String getSub_button() {
		return sub_button;
	}
	public void setSub_button(String sub_button) {
		this.sub_button = sub_button;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMiniprogram() {
		return miniprogram;
	}
	public void setMiniprogram(String miniprogram) {
		this.miniprogram = miniprogram;
	}

}
