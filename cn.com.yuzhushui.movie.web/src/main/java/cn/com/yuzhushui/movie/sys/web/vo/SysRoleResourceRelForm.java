package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:55:04
 * @history
 */
@Getter
@Setter
public class SysRoleResourceRelForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields role_id:role_id
	 */
	private Integer roleId;
	
	/**
	 * @Fields resource_id:resource_id
	 */
	private Integer resourceId;
	
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