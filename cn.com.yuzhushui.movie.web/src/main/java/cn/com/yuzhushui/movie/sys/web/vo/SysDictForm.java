package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:51:36
 * @history
 */
@Getter
@Setter
public class SysDictForm extends BaseForm<Integer> {

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
	 * @Fields item_key:键
	 */
	private String itemKey;
	
	/**
	 * @Fields item_value:值
	 */
	private String itemValue;
	
	/**
	 * @Fields name:名称
	 */
	private String name;
	
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