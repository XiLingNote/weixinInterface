package com.wxpay.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import POJO.WXPayBean;


public class WXUtils {

	private String getAddrIp(HttpServletRequest request){ 
	    return request.getRemoteAddr(); 
	  } 
	  
 /** 
   * map转成xml 
   * 
   * @param arr 
   * @return 
   */
  public static String ArrayToXml(Map<String, String> arr) { 
    String xml = "<xml>"; 
  
    Iterator<Entry<String, String>> iter = arr.entrySet().iterator(); 
    while (iter.hasNext()) { 
      Entry<String, String> entry = iter.next(); 
      String key = entry.getKey(); 
      String val = entry.getValue(); 
      xml += "<" + key + ">" + val + "</" + key + ">"; 
    } 
  
    xml += "</xml>"; 
    return xml; 
  } 
  
  /**
	 * 微信支付 MD5签名
	 * @throws Exception 
	 */
	public static WXPayBean wxMD5Sign(String appId,String key,String packageStr) throws Exception{
		String signType="MD5";
		String nonceStr=CreateRandom.createRandom(false, 32);
		long timeStamp = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
		
		String wait_sign="appId="+appId+"&nonceStr="+nonceStr+"&package="
				+packageStr+"&signType="+signType+"&timeStamp="+timeStamp
				+"&key="+key;
		
		String paySign=MD5Util.md5EncodeOrigin(wait_sign).toUpperCase();
		
		WXPayBean wxPayBean=new WXPayBean();
		wxPayBean.setAppId(appId);
		wxPayBean.setNonceStr(nonceStr);
		wxPayBean.setPackageStr(packageStr);
		wxPayBean.setSignType(signType);
		wxPayBean.setTimeStamp(timeStamp+"");
		wxPayBean.setPaySign(paySign);
		
		return wxPayBean;
	}
	
	  /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static SortedMap<String, String> doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		SortedMap<String, String> m = new TreeMap<String, String>();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
  public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
  
  /**
   * InputStream流转换成String字符串
   * @param inStream InputStream流
   * @param encoding 编码格式
   * @return String字符串
   */
  public static String inputStream2String(InputStream inStream, String encoding){
   String result = null;
   try {
   if(inStream != null){
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    byte[] tempBytes = new byte[1024];
    int count = -1;
    while((count = inStream.read(tempBytes)) != -1){
      outStream.write(tempBytes, 0, count);
    }
    tempBytes = null;
    outStream.flush();
    result = new String(outStream.toByteArray(), encoding);
   }
   } catch (Exception e) {
   result = null;
   }
   return result;
  }
  
  public static boolean checkSign(String xmlString) {

	  SortedMap<String, String> map = new TreeMap<String, String>();

      try {

          map = WXUtils.doXMLParse(xmlString);

      } catch (Exception e) {
          e.printStackTrace();
      }

      String signFromAPIResponse = map.get("sign").toString();

      if (signFromAPIResponse == "" || signFromAPIResponse == null) {

          System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改!!!");

          return false;

      }
      System.out.println("服务器回包里面的签名是:" + signFromAPIResponse);

      //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名

      map.put("sign", "");

      //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
      //签名
	    RequestHandler reqHandler = new RequestHandler(null, null);
	    String signForAPIResponse = reqHandler.createSign(map);

      if (!signForAPIResponse.equals(signFromAPIResponse)) {

          //签名验不过，表示这个API返回的数据有可能已经被篡改了

          System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改!!! signForAPIResponse生成的签名为" + signForAPIResponse);

          return false;

      }

      System.out.println("恭喜，API返回的数据签名验证通过!!!");

      return true;

  }
  
}
