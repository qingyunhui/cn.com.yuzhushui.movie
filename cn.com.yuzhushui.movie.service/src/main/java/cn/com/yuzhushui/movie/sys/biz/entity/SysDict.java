package cn.com.yuzhushui.movie.sys.biz.entity;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 00:35:43
 * @history
 */
@Getter
@Setter
public class SysDict extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysDict";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields pid:pid
	 */
	private Integer pid;
	
	/**
	 * @Fields itemKey:键
	 */
	private String itemKey;
	
	/**
	 * @Fields itemValue:值
	 */
	private String itemValue;
	
	/**
	 * @Fields name:名称
	 */
	private String name;
	
	/**
	 * @Fields orderValue:排序
	 */
	private Integer orderValue;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields createrId:创建人ID
	 */
	private Integer createrId;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields editor:修改人
	 */
	private String editor;
	
	/**
	 * @Fields editorId:修改人ID
	 */
	private Integer editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

}