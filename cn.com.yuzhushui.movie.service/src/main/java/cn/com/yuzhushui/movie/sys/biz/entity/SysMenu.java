package cn.com.yuzhushui.movie.sys.biz.entity;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:51:37
 * @history
 */
@Getter
@Setter
public class SysMenu extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysMenu";
	
	//columns START
	/**
	 * @Fields menuId:menu_id
	 */
	private Integer menuId;
	
	/**
	 * @Fields menuPid:menu_pid
	 */
	private Integer menuPid;
	
	/**
	 * @Fields menuName:菜单名称
	 */
	private String menuName;
	
	/**
	 * @Fields sysResourceId:资源ID
	 */
	private Integer sysResourceId;
	
	/**
	 * @Fields url:连接地址
	 */
	private String url;
	
	/**
	 * @Fields style:样式
	 */
	private String style;
	
	/**
	 * @Fields level:级别
	 */
	private Integer level;
	
	/**
	 * @Fields isLeaf:是否叶子
	 */
	private Integer isLeaf;
	
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
		return menuId;
	}
	
	//columns END

}