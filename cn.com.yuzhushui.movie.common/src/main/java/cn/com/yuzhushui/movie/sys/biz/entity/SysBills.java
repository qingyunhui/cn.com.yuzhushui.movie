package cn.com.yuzhushui.movie.sys.biz.entity;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
@Getter
@Setter
public class SysBills extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysBills";
	
	//columns START
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields subject:主题
	 */
	private String subject;
	
	/**
	 * @Fields content:说明
	 */
	private String content;
	
	/**
	 * @Fields debtor:借款人
	 */
	private String debtor;
	
	/**
	 * @Fields debtorId:借款id
	 */
	private Integer debtorId;
	
	/**
	 * @Fields money:借款金额
	 */
	private Long money;
	
	/**
	 * @Fields lender:出借人
	 */
	private Integer lender;
	
	/**
	 * @Fields lenderId:出借人id
	 */
	private Integer lenderId;
	
	/**
	 * @Fields status:状态
	 */
	private Integer status;
	
	/**
	 * @Fields tradeType:交易类型
	 */
	private Integer tradeType;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields createrId:创建人id
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
	 * @Fields editorId:修改人id
	 */
	private Integer editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

}