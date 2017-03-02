package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-02 22:50:29
 * @history
 */
@Getter
@Setter
public class SysFundPoolForm extends BaseForm<Integer>{

	//columns START
	
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields account_id:资金池拥有者id
	 */
	private Integer accountId;
	
	/**
	 * @Fields gold:现金
	 */
	private Long gold;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields creater_id:创建人id
	 */
	private String createrId;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	//columns END

}