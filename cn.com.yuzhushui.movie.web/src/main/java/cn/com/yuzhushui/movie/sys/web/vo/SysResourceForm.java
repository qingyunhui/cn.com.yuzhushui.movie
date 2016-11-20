package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:55:03
 * @history
 */
@Getter
@Setter
public class SysResourceForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields resource_id:resource_id
	 */
	private Integer resourceId;
	
	/**
	 * @Fields pid:pid
	 */
	private Integer pid;
	
	/**
	 * @Fields resource_name:资源名称
	 */
	private String resourceName;
	
	/**
	 * @Fields url:资源地址
	 */
	private String url;
	
	/**
	 * @Fields type:类型(1.系统、2.用户、3.权限)
	 */
	private Integer type;
	
	/**
	 * @Fields status:状态(待定)
	 */
	private Integer status;
	
	/**
	 * @Fields order_value:排序
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
	 * @Fields creater_id:创建人ID
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
	 * @Fields editor_id:修改人ID
	 */
	private Integer editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	//columns END
}