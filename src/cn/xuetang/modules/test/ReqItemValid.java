package cn.xuetang.modules.test;

import com.xtc.gsdata.api.XtDate;

import cn.xuetang.modules.test.ReqItemSaveCondition.SaveConditionEnum;

public class ReqItemValid {
	
	public static boolean reqItemIsValid(ReqItem item) 
	{	
		boolean bResult = false ;
				
		String name = item.getName() ;
		SaveConditionEnum conditionEnum = ReqItemSaveCondition.getSaveConditionWithRegItemName(name) ;
		switch (conditionEnum) {
		case oneDay :
		{
			bResult = ! XtDate.isMoreThanOneDay(item.getDate()) ;
		}
			break;
		case oneWeek :
		{
			bResult = ! XtDate.isMoreThanOneWeek(item.getDate()) ;
		}
			break;
		case forever :
		{
			bResult = true ;
		}
			break;
		case halfDay :
		{
			bResult = ! XtDate.isMoreThanHalfDay(item.getDate()) ;
		}
			break;
		default:
			break;
		}				
		
		return bResult ;
	}
	
}
