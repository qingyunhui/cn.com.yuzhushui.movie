package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-16 22:13:42
 * @history
 */
public class SysMenuVO {

	//columns START
	
	/**
	 * @Fields menu_id:menu_id
	 */
	private Integer menuId;
	
	/**
	 * @Fields menu_pid:menu_pid
	 */
	private Integer menuPid;
	
	/**
	 * @Fields menu_name:菜单名称
	 */
	private String menuName;
	
	/**
	 * @Fields sys_resource_id:资源ID
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
	 * @Fields is_leaf:是否叶子
	 */
	private Integer isLeaf;
	
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

	public SysMenuVO(){
	}

	public SysMenuVO(Integer menuId){
		this.menuId = menuId;
	}

	
	public void setMenuId(Integer menuId){
		this.menuId = menuId;
	}
	public Integer getMenuId(){
		return menuId;
	}
	
	public void setMenuPid(Integer menuPid){
		this.menuPid = menuPid;
	}
	public Integer getMenuPid(){
		return menuPid;
	}
	
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public String getMenuName(){
		return menuName;
	}
	
	public void setSysResourceId(Integer sysResourceId){
		this.sysResourceId = sysResourceId;
	}
	public Integer getSysResourceId(){
		return sysResourceId;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return url;
	}
	
	public void setStyle(String style){
		this.style = style;
	}
	public String getStyle(){
		return style;
	}
	
	public void setLevel(Integer level){
		this.level = level;
	}
	public Integer getLevel(){
		return level;
	}
	
	public void setIsLeaf(Integer isLeaf){
		this.isLeaf = isLeaf;
	}
	public Integer getIsLeaf(){
		return isLeaf;
	}
	
	public void setOrderValue(Integer orderValue){
		this.orderValue = orderValue;
	}
	public Integer getOrderValue(){
		return orderValue;
	}
	
	public void setCtime(Date ctime){
		this.ctime = ctime;
	}
	public Date getCtime(){
		return ctime;
	}
	
	public void setCreater(String creater){
		this.creater = creater;
	}
	public String getCreater(){
		return creater;
	}
	
	public void setCreaterId(Integer createrId){
		this.createrId = createrId;
	}
	public Integer getCreaterId(){
		return createrId;
	}
	
	public void setEtime(Date etime){
		this.etime = etime;
	}
	public Date getEtime(){
		return etime;
	}
	
	public void setEditor(String editor){
		this.editor = editor;
	}
	public String getEditor(){
		return editor;
	}
	
	public void setEditorId(Integer editorId){
		this.editorId = editorId;
	}
	public Integer getEditorId(){
		return editorId;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	public String getComments(){
		return comments;
	}

}