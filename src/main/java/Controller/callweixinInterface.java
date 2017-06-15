package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import POJO.ButtonBean;
import POJO.GetToken;
import util.JsonUtil;

public class callweixinInterface {

	public static String sendGet(String jsonStr, String path) throws Exception {
		byte[] data = jsonStr.getBytes();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		conn.setReadTimeout(20 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		OutputStream outStream = conn.getOutputStream();// 返回写入到此连接的输出流
		outStream.write(data);
		String msg = "";
		if (conn.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			msg = in.readLine();
			in.close();
		}
		conn.disconnect();
		return msg;
	}
	
	public static void main(String[] args) throws Exception {
		//请求token
		String Tokenmsg=sendGet("","https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2eb457be2f097b63&secret=0058cf195b97b6af7fe2d98fa044e06f");
			
		//1.
		ButtonBean button= new ButtonBean();

		ButtonBean button1= new ButtonBean();
		button1.setType("Click");
		button1.setName("今日歌曲");
		button1.setKey("V1001_TODAY_MUSIC");
		ButtonBean button2= new ButtonBean();
		button2.setName("菜单");
		ButtonBean button3= new ButtonBean();
		button3.setType("View");
		button3.setName("搜索");
		button3.setUrl("http://www.soso.com/");
		ButtonBean button4=new ButtonBean();
		button4.setType("miniprogram");
		button4.setName("War");
		button4.setUrl("http://mp.weixin.qq.com");
		button4.setAppid("wx2eb457be2f097b63");
		button4.setPagepath("");
		ButtonBean button5=new ButtonBean();
		button5.setType("click");
		button5.setName("赞我一下");
		button5.setKey("V1001_GOOD");
		List<ButtonBean> subButton= new ArrayList<ButtonBean>();
		List<ButtonBean> buttonl= new ArrayList<ButtonBean>();
			buttonl.add(button1);
			subButton.add(button3);
			subButton.add(button4);
			button2.setSubButton(subButton);
			buttonl.add(button2);
			buttonl.add(button5);
			button.setButton(buttonl);
			
			ButtonBean[] beanarray=buttonl.toArray(new ButtonBean[buttonl.size()]);
					String buttonmsg=sendGet(JsonUtil.beanToJson(button),"https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ JsonUtil.jsonToBean(Tokenmsg, GetToken.class).getAccess_token());
		System.out.println(buttonmsg);
		System.out.println(JsonUtil.beanToJson(button));
		System.out.println();
		
	}

}
