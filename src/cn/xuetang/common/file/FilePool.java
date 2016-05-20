package cn.xuetang.common.file;

import org.nutz.filepool.NutFilePool;
import org.nutz.mvc.Mvcs;

/**
 * 类描述： 设置文件池路径及大小
 * 创建人：shiyq
 * 联系方式：www.shiyq.cn
 * 创建时间：2013-12-16 上午9:54:21
 */
public class FilePool extends NutFilePool {

    public FilePool(String homePath, long size) {
        super(webinfPath(homePath), size);
    }

    private static final String webinfPath(String str) {
        return Mvcs.getServletContext().getRealPath("/WEB-INF") + str;
    }

}
