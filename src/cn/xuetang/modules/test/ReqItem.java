package cn.xuetang.modules.test;

import java.util.Date;

import org.nutz.dao.entity.annotation.*;

@Table("req")   // table name .

public class ReqItem {
	
	@Name 		// 字符型主键，或者是唯一性约束
	@ColDefine(type = ColType.VARCHAR , width = 255)
	private String name ;
	
    @ColDefine(type = ColType.TEXT)  // (customType = "LONGTEXT")	// 表示该对象属性可以映射到数据库里作为一个字段
    private String val ;
    
    @ColDefine (type = ColType.DATE)
    private Date date ;
    
//    -------------------------------------------------
    
    public ReqItem() {
		// TODO Auto-generated constructor stub
	}
    public ReqItem(String name,String val,Date date) {
		// TODO Auto-generated constructor stub
    	this.setName(name);
    	this.setVal(val);
    	this.setDate(date);
	}
    
    public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name ;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getVal() {
		return val ;
	}
	
    public void setVal(String val) {
    	this.val = val ;		
	}
}
