package cn.xuetang.modules.test;

public class ReqItemSaveCondition {
	
	public enum SaveConditionEnum {
		oneDay  ,	 // Default  
		oneWeek ,
		forever ,
		halfDay 
	}

	public static SaveConditionEnum getSaveConditionWithRegItemName(String name) {
		
		SaveConditionEnum enum1 = SaveConditionEnum.oneDay ; // default is one day .
		
		if (name.matches("wx/wxapi/group_name")) {
			enum1 = SaveConditionEnum.oneWeek ;
		}
		else if (name.matches("wx/wxapi/nickname_one")) {
			enum1 = SaveConditionEnum.forever ;
		}
		else if (name.matches("wx/wxapi/nickname")) { 
			enum1 = SaveConditionEnum.oneWeek ;
		}
		else if (name.matches("wx/opensearchapi/nickname_order_list")) {
			enum1 = SaveConditionEnum.halfDay ;
		}
		
		return enum1 ;
	}
	
}
