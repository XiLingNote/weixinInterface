package com.wxpay;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxpay.util.CreateRandom;
import com.wxpay.util.GetWxOrderno;
import com.wxpay.util.MD5Util;
import com.wxpay.util.RequestHandler;
import com.wxpay.util.Sha1Util;
import com.wxpay.util.TimeUtils;
import com.wxpay.util.WXUtils;

public class WeiXinTest {

	public void methodOne(HttpServletRequest request,HttpServletResponse response)
	{
		String order_no = "";
		float total_price = 0;

		
		String appid = ""; //微信公众号appid
		String appsecret = ""; //微信公众号密匙
	    String partnerkey = "";  //微信商户密匙
	    String attach="test";  
	    String body="test_pay";  
	    String mch_id="";  //微信商户id
	    String nonce_str=CreateRandom.createRandom(false, 32);  //随机数
	    String openid="";  //用户的openid，如果是微信商城，在第一次登陆的时候就已经保存在数据库了，我这里就没做获取openid的做法
	    String out_trade_no= TimeUtils.getCurrentTime("yyyyMMddHHmmss");  //订单号
	    String spbill_create_ip=request.getRemoteAddr(); //ip地址， 注意：在本地测试不了，会报签名错误，你只需在微信官方提供的测试签名中通过就ok了，然后上传服务器
	    if(spbill_create_ip.equals("0:0:0:0:0:0:0:1%0"))
	    {
	    	spbill_create_ip = "172.168.0.1";
	    }
	    String total_fee= String.valueOf(total_price); //订单总价
	    String trade_type="JSAPI"; //这是H5支付
	    String notify_url="http://mbsx.tstweiguanjia.com/orderInterfaces.api?wxpaySuccessOrder";  //这是支付成功后所回调的方法
	    String sign_type="MD5";  //MD5
	    
//	    Map<String, String> paraMap = new HashMap<String, String>(); 
	    SortedMap<String, String> paraMap = new TreeMap<String, String>();
	    paraMap.put("appid", appid); 
	    paraMap.put("attach",attach); 
	    paraMap.put("body", body); 
	    paraMap.put("mch_id", mch_id); 
	    paraMap.put("nonce_str",nonce_str); 
	   
	    paraMap.put("openid",openid); 
	    paraMap.put("out_trade_no", out_trade_no); 
	    paraMap.put("spbill_create_ip", spbill_create_ip); 
	    paraMap.put("total_fee",total_fee); 
	    paraMap.put("trade_type",trade_type); 
	    paraMap.put("notify_url", notify_url);
	    paraMap.put("sign_type", sign_type); 
	    
	    
//	    String wait_sign="appid="+appid
//	    			+"&attach="+attach
//	    			+"&body="+body
//	    			+"&mch_id="+mch_id
//	    			+"&nonce_str="+nonce_str
//	    			+"&notify_url="+notify_url
//	    			+"&openid="+openid
//	    			+"&out_trade_no="+out_trade_no
//	    			+"&sign_type="+sign_type
//	    			+"&spbill_create_ip="+spbill_create_ip
//	    			+"&total_fee="+total_fee
//	    			+"&trade_type="+trade_type
//	    			+"&key="+partnerkey;
//	    
//	    String sign=MD5Util.md5EncodeOrigin(wait_sign).toUpperCase();
	    //这一块是签名
	    RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
	    String sign = reqHandler.createSign(paraMap);
	    System.out.println(sign);
	    paraMap.put("sign", sign); 
	    
	    
	 // 统一下单 https://apimchweixinqqcom/pay/unifiedorder 
	    String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	  
//	    String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
//		+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
//		+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
//		+ "<body><![CDATA[" + body + "]]></body>"
//		+ "<out_trade_no>"
//		+ out_trade_no
//		+ "</out_trade_no>"
//		+
//
//		// 金额，这里写的1 分到时修改
//		"<total_fee>"
//		+ 1
//		+ "</total_fee>"
//		+
//		// "<total_fee>"+finalmoney+"</total_fee>"+
//		"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
//		+ "<notify_url>" + notify_url + "</notify_url>"
//		+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
//		+ openid + "</openid>" + "</xml>";
	    String xml = WXUtils.ArrayToXml(paraMap); //将m转化为xml形式
	    String prepay_id = "";
		try {
			System.out.println(xml);
			prepay_id = new GetWxOrderno().getPayNo(url, xml);
			if (prepay_id.equals("")) {
				System.out.println("统一支付接口获取预支付订单出错");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("prepay_id:" + prepay_id);
		
		//如果拿到了微信返回的预付订单id，表示已经成功了，接下来就是再次验证严明，将数据返给前段
//		Map<String, String> paraMap = new HashMap<String, String>(); 
	    SortedMap<String, String> packageParams = new TreeMap<String, String>();
	    
	    //注意：这次签名必须加上时间戳，不然会弹不出支付的框
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		packageParams.put("appId", appid2); 
		packageParams.put("timeStamp",timestamp); 
		packageParams.put("nonceStr", nonce_str); 
		packageParams.put("package", packages); 
		packageParams.put("signType", sign_type); 
		String finalsign = reqHandler.createSign(packageParams);
		String str = "timestamp:\""
				+ timestamp // 这里的也是小写~~
				+ "\",nonceStr:\"" + nonceStr2 + "\",package:\"" + packages
				+ "\",signType: \"MD5" + "\",paySign:\"" + finalsign + "\"";
		packageParams.put("paySign", finalsign);
   
		//签名之后，finalsign 是最终的结果， 你可以转成json，给前段
	    
//	 // 预付商品id 
//	    String prepay_id = ""; 
//	    System.out.println(prepay_id);
//	    WriteOnlyMsg(response, xmlStr+"==="+xml);
//	    if (xmlStr.indexOf("SUCCESS") != -1) { 
//	      Map<String, String> map = doXMLParse(xmlStr); 
//	      prepay_id = (String) map.get("prepay_id"); 
//	    } 
//	    System.out.println(prepay_id+"------------------------");
	}
	
	public void methodTow(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String order_no = "";
		float total_price = 0;

		
		String appid = ""; //微信公众号appid
		String appsecret = ""; //微信公众号密匙
	    String partnerkey = "";  //微信商户密匙
	    String attach="test";  
	    String body="test_pay";  
	    String mch_id="";  //微信商户id
	    String nonce_str=CreateRandom.createRandom(false, 32);  //随机数
	    String openid="";  //用户的openid，如果是微信商城，在第一次登陆的时候就已经保存在数据库了，我这里就没做获取openid的做法
	    String out_trade_no= TimeUtils.getCurrentTime("yyyyMMddHHmmss");  //订单号
	    String spbill_create_ip=request.getRemoteAddr(); //ip地址， 注意：在本地测试不了，会报签名错误，你只需在微信官方提供的测试签名中通过就ok了，然后上传服务器
	    if(spbill_create_ip.equals("0:0:0:0:0:0:0:1%0"))
	    {
	    	spbill_create_ip = "172.168.0.1";
	    }
	    String total_fee= String.valueOf(total_price); //订单总价
	    String trade_type="JSAPI"; //这是H5支付
	    String notify_url="http://mbsx.tstweiguanjia.com/orderInterfaces.api?wxpaySuccessOrder";  //这是支付成功后所回调的方法
	    String sign_type="MD5";  //MD5
	    
	    Map<String, String> paraMap = new HashMap<String, String>(); 
	    paraMap.put("appid", appid); 
	    paraMap.put("attach",attach); 
	    paraMap.put("body", body); 
	    paraMap.put("mch_id", mch_id); 
	    paraMap.put("nonce_str",nonce_str); 
	   
	    paraMap.put("openid",openid); 
	    paraMap.put("out_trade_no", out_trade_no); 
	    paraMap.put("spbill_create_ip", spbill_create_ip); 
	    paraMap.put("total_fee",total_fee); 
	    paraMap.put("trade_type",trade_type); 
	    paraMap.put("notify_url", notify_url);
	    paraMap.put("sign_type", sign_type); 
	    
	    
	    String wait_sign="appid="+appid
	    			+"&attach="+attach
	    			+"&body="+body
	    			+"&mch_id="+mch_id
	    			+"&nonce_str="+nonce_str
	    			+"&notify_url="+notify_url
	    			+"&openid="+openid
	    			+"&out_trade_no="+out_trade_no
	    			+"&sign_type="+sign_type
	    			+"&spbill_create_ip="+spbill_create_ip
	    			+"&total_fee="+total_fee
	    			+"&trade_type="+trade_type
	    			+"&key="+partnerkey;
	    
	    String sign=MD5Util.md5EncodeOrigin(wait_sign).toUpperCase();
	    
	    
	 // 统一下单 https://apimchweixinqqcom/pay/unifiedorder 
	    String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	    String xml = WXUtils.ArrayToXml(paraMap); //将m转化为xml形式
	    String prepay_id = "";
		try {
			System.out.println(xml);
			prepay_id = new GetWxOrderno().getPayNo(url, xml);
			if (prepay_id.equals("")) {
				System.out.println("统一支付接口获取预支付订单出错");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("prepay_id:" + prepay_id);
		
		//如果拿到了微信返回的预付订单id，表示已经成功了，接下来就是再次验证严明，将数据返给前段
		Map<String, String> packageParams = new HashMap<String, String>(); 
	    
	    //注意：这次签名必须加上时间戳，不然会弹不出支付的框
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id; //这个格式，是前段需要的
		String packages = prepay_id2;
		packageParams.put("appId", appid2); 
		packageParams.put("timeStamp",timestamp); 
		packageParams.put("nonceStr", nonce_str); 
		packageParams.put("package", packages); 
		packageParams.put("signType", sign_type); 
		
		 String wait_sign1="appid="+appid2
			+"&timeStamp="+timestamp
			+"&nonceStr="+nonce_str
			+"&package="+packages
			+"&sign_type="+sign_type;

        String finalsign=MD5Util.md5EncodeOrigin(wait_sign1).toUpperCase();
		String str = "timestamp:\""
				+ timestamp // 这里的也是小写~~
				+ "\",nonceStr:\"" + nonceStr2 + "\",package:\"" + packages
				+ "\",signType: \"MD5" + "\",paySign:\"" + finalsign + "\"";
		packageParams.put("paySign", finalsign);
   
		//签名之后，finalsign 是最终的结果， 你可以转成json，给前段
	    
//	 // 预付商品id 
//	    String prepay_id = ""; 
//	    System.out.println(prepay_id);
//	    WriteOnlyMsg(response, xmlStr+"==="+xml);
//	    if (xmlStr.indexOf("SUCCESS") != -1) { 
//	      Map<String, String> map = doXMLParse(xmlStr); 
//	      prepay_id = (String) map.get("prepay_id"); 
//	    } 
//	    System.out.println(prepay_id+"------------------------");
	}
}
