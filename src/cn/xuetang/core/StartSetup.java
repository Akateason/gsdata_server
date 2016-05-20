package cn.xuetang.core;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import cn.xuetang.modules.test.ReqItem;

/**
 * 类描述： 创建人：shiyq 联系方式：www.shiyq.cn 创建时间：2013-11-26 下午2:11:13
 */

public class StartSetup implements Setup {
    private final Log log = Logs.get();
    public static Dao dao ;
    
    @Override
    public void init(NutConfig config) {
        try {
            dao = config.getIoc().get(Dao.class);
            Daos.createTablesInPackage(dao, "cn.xuetang.modules.test", false);

            
//        	ReqItem item = new ReqItem("aaa", "23bbb") ;
//            dao.insert(item) ;
                        
//    		dao.query(ReqItem.class, Cnd.where("name", "=", "aaaaakeyname"));

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    @Override
    public void destroy(NutConfig config) {

    }
}
