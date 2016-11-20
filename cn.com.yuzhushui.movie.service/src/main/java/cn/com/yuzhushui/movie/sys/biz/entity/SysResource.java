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
public class SysResource extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysResource";
	
	//columns START
	/**
	 * @Fields resourceId:resource_id
	 */
	private Integer resourceId;
	
	/**
	 * @Fields pid:pid
	 */
	private Integer pid;
	
	/**
	 * @Fields resourceName:资源名称
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
	 * @Fields deleted:是否删除
	 */
	private Integer deleted;

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return resourceId;
	}
	
	//columns END

}