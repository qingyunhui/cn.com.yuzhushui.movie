package cn.com.yuzhushui.movie.sys.biz.entity;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:55:01
 * @history
 */
@Getter
@Setter
public class SysOprationLog extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysOprationLog";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields type:操作类型
	 */
	private Integer type;
	
	/**
	 * @Fields content:操作内容
	 */
	private String content;
	
	/**
	 * @Fields ip:操作人IP地址
	 */
	private String ip;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:操作人
	 */
	private String creater;
	
	/**
	 * @Fields createrId:操作人ID
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
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

}