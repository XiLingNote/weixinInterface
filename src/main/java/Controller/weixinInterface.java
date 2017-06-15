package Controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("weixin")
public class weixinInterface {
	Logger log = Logger.getLogger(weixinInterface.class);

	@RequestMapping("testsubmit")
	public void returnIndex(HttpServletRequest request, HttpServletResponse response, String signature,
			String timestamp, String nonce, String echostr) throws IOException {
		final String token = "jared";
		// ����
		log.info(timestamp + "-----------------------------------------");
		String sortString = sort(token, timestamp, nonce);

		// ����
		String mytoken = SHA1(sortString);
		// У��ǩ��
		if (mytoken != null && mytoken != "" && mytoken.equals(signature)) {
			log.info("ǩ��У��ͨ����");
			response.getWriter().println(echostr); // �������ɹ����echostr��΢�ŷ��������յ���������Ż�ȷ�ϼ�����ɡ�
		} else {
			log.info("ǩ��У��ʧ�ܡ�");
		}
	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// �ֽ�����ת��Ϊ ʮ������ ��
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ���򷽷�
	 * 
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce };
		Arrays.sort(strArray);

		StringBuilder sbuilder = new StringBuilder();
		for (String str : strArray) {
			sbuilder.append(str);
		}

		return sbuilder.toString();
	}
}
