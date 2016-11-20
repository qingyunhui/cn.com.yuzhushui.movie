package cn.com.yuzhushui.movie.sys.biz.entity;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseModel;
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
public class SysRole extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysRole";
	
	//columns START
	/**
	 * @Fields roleId:role_id
	 */
	private Integer roleId;
	
	/**
	 * @Fields roleName:角色名称
	 */
	private String roleName;
	
	/**
	 * @Fields resourceId:默认主页url
	 */
	private String resourceId;
	
	/**
	 * @Fields status:状态(0.停用、1.启用)
	 */
	private Integer status;
	
	/**
	 * @Fields type:角色类型(0.普通、1.VIP、2.管理员)
	 */
	private Integer type;
	
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
	 * @Fields deleted:是否删除
	 */
	private Integer deleted;

	@Override
	public Integer getId() {
		return roleId;
	}
	
	//columns END

}