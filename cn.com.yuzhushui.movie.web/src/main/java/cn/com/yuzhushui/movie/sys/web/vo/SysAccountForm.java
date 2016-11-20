package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:37:51
 * @history
 */
@Getter
@Setter
public class SysAccountForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields account_id:account_id
	 */
	private Integer accountId;
	
	/**
	 * @Fields account:账号
	 */
	private String account;
	
	/**
	 * @Fields password:密码(加密后)
	 */
	private String password;
	
	/**
	 * @Fields status:状态(0.未审核、1.审核不通过、2.审核通过、3.停用)
	 */
	private Integer status;
	
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