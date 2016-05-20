package cn.xuetang.modules.test;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;


import com.google.gson.JsonObject;
import com.xtc.gsdata.api.GsdataOperation;
import com.xtc.gsdata.api.JsonToMap;
import com.xtc.gsdata.api.XtDate;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author teason
 * Main .
 */
@IocBean
@At("/api")
@Ok("json")
public class TestAction {

    @At("")
    @Ok("raw")

    /**
     * MAIN ACITON .
     * @param spaceName
     * @param jsonStr
     * @param req
     * @return JSON STRING .
     */
    public String apiServer(@Param("spaceName")String spaceName, @Param("jsonStr")String jsonStr, 
    		HttpServletRequest req) {
    	
        try {        	
        	Map<String, Object> paramMap = new HashMap<String,Object>() ; // new map .
    		if (jsonStr.length() != 0) {
        		paramMap = JsonToMap.toMap(jsonStr); // make str to map .
			}    		
    		String resultString = "" ;
    		String keyName = spaceName + jsonStr ; // combined key name .
    		
    		// 1. fetch reqItem from sql .     		
    		ReqItem itemFromSql = DaoAction.fetchItemWithKey(keyName) ; // get item from sql .
    		String valueString = itemFromSql.getVal() ; // get val str
    		 
    		// 1.1* DATA NOT EXIST .
    		if (valueString == null) 
    		{
    			// 2.1 SEND REQUEST to GSDATA server .
    			resultString = GsdataOperation.requestGsApi(spaceName, paramMap) ; // get result string .
    			
    			// 2.1.1 request success 
    			if (resultString != null) 
    			{
    				// JUDGE CONDITION CAN BE INSERT ? CAN BE CACHE ?
    				// Parse 1 get 'returndata.total',  2 get 'returndata.errcode' 
    				if (!resultCanBeSaveInMySq(resultString)) return resultString ; // if cannot be saved return immediately					
    				
    				// item can be insert . can be saved . so insert it !
    				// 3.1 INSERT TO SQL . FIRST TIME .
    				Date today = XtDate.getCurrentDayOriginalDate() ;    				    				
        			ReqItem item = new ReqItem(keyName, resultString, today) ; // insert sql .
        			DaoAction.insertItem(item) ;	
				}
			}
    		// 1.2* DATA EXIST . MEANS THE ITEM CAN BE INSERT . 
    		else {
    			// 2 .JUDGE REQITEM IS VALID OR NOT ?    			
    			if ( ReqItemValid.reqItemIsValid(itemFromSql) ) 
    			{ // 2.1  valid
    				resultString = valueString ; // return immediately
				}
    			else 
    			{ // 2.2 not valid 
					// 	REQUEST TO GSDATA SERVER AND EXCUTE UPDATE SQL .
        			resultString = GsdataOperation.requestGsApi(spaceName, paramMap) ; // get result string .
        			if (resultString != null) {
						// 	do update .
        				// 3.2 UPDATE TO SQL . 
        				Date today = XtDate.getCurrentDayOriginalDate() ;    				    				
            			ReqItem item = new ReqItem(keyName, resultString, today) ; // insert sql .
            			DaoAction.updateItem(item) ;
					}
				}    			    			 			    	
			}
    		
        	return resultString ;
        	
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * CAN BE INSERT CONDITION .
     * @param resultString
     * @return
     */
    public boolean resultCanBeSaveInMySq(String resultString) 
    {		
    	JsonObject resultMap = JsonToMap.parseJson(resultString) ;
		JsonObject resultData = resultMap.get("returnData").getAsJsonObject() ;
		// 调用成功但 数据为零 total == 0
		if (resultData.has("total")) {
			int total = resultData.get("total").getAsInt() ;
			if (total == 0) {
				return false ;
			}
		}
		// 调用成功但 统计表不存在 errcode == 2
		if (resultData.has("errcode")) {
			int errcode = resultData.get("errcode").getAsInt() ;
			if (errcode == 2) {
				return false ;
			}
		}    				
    	return true ;
	}
    
}