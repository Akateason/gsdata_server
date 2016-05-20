package cn.xuetang.modules.test;

import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import cn.xuetang.core.StartSetup;

public class DaoAction {
	
	static Dao dao = StartSetup.dao ; ;
	
	public static void insertItem (ReqItem item) {
		dao.insert(item) ;
	}
	
	public static void updateItem (ReqItem item) {
		dao.update(item) ;
	}
	
	public static ReqItem fetchItemWithKey(String key) {
		ReqItem item = new ReqItem();
		
		List<ReqItem> reqList = dao.query(ReqItem.class, Cnd.where("name", "like", key));
		
		if (reqList.size() > 0) {
			item = reqList.get(0) ;			
		}
		
		return item ;
	}
	

}
