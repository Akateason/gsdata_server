package com.xtc.gsdata.api;

import java.util.Map;
import cn.gsdata.index.ApiSdk;

public class GsdataOperation {

	private final static String appId = "3F5UUizgQndATwsT4870";				// 你的appId
	private final static String appKey = "onMc2rKwiq0T4p6J6dj4gI23l";		// 你的appKey
	private final static String host = "http://open.gsdata.cn/api";			// 默认域名

	public static String requestGsApi(String spaceName, Map<String, Object> map) {
		// 获取ApiSdk 单例对象						
		String urlString = host + "/" + spaceName;		
		//System.out.println("\n"+urlString+"\n"+map+"\n");
		ApiSdk apiSdk = ApiSdk.getApiSdk(appId, appKey);		
		String ret_json = apiSdk.callInterFace(urlString, map);	
		System.out.println("gsdata返回: " + ret_json);
		return ret_json;
	}

}
