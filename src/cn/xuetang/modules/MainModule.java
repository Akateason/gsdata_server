package cn.xuetang.modules;

import cn.xuetang.core.StartSetup;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * 类描述：
 * 创建人：shiyq
 * 联系方式：www.shiyq.cn
 * 创建时间：2013-11-26 下午2:08:37
 */

@Modules(scanPackage = true)
@Ok("raw")
@Fail("http:500")
@IocBy(type = ComboIocProvider.class, args = {
        "*org.nutz.ioc.loader.json.JsonLoader", "config",
        "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "cn.xuetang","com.xtc.gsdata.api"
		})
@SetupBy(value = StartSetup.class)
public class MainModule {
}
