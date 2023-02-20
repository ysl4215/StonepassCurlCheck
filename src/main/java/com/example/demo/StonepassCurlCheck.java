package com.example.demo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.util.Curl;
import com.example.util.Mail;

public class StonepassCurlCheck {
	
	private final static Logger logger = LoggerFactory.getLogger(StonepassCurlCheck.class);
	private static String Url = "http://192.168.0.44:8080/stonepass/sp/v1/fido/request";
	private static String[] headers = {"Content-type application/json"};
	private static String statusCode;
	private static JSONObject jsonObject;
	
	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args){
	
		JSONObject jsonReq = new JSONObject();
		String ssenu = "{\"userName\":\"1\",\"token\":\"b0ptsMg1xl6_JmJyquuiew\",\"SYSTEMID\":\"201103001\",\"MODEL\":\"SM-A826S\",\"BIOTYPE\":\"PINCODE\",\"DEVICE\":\"AND:123456789098765b25b93041b0f1ba6\"}";
		
		
		jsonReq.put("op", "Reg");
		jsonReq.put("context", ssenu);
		 
		String result = Curl.post(Url, headers, jsonReq.toString());
		
//		logger.info("[StonepassCurlCheck] main() response : " + result);
		
		JSONParser parser = new JSONParser();
		
		try {
			jsonObject = (JSONObject) parser.parse(result);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		statusCode = String.valueOf(jsonObject.get("statusCode"));
		
		if(Integer.parseInt((String)String.valueOf(statusCode)) == 1200) {
			System.out.println("StonePASS 통합인증앱 정상동작");
		} else {
			System.out.println("StonePASS 통합인증앱 에러발생 ");
//			logger.info("[StonepassCurlCheck] main() statusCode : " + statusCode);
			Mail.mailSend();
		}
	}


}
