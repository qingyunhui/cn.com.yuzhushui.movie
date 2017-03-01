package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
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
public class SysBillsForm extends BaseForm<Integer> {

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
	 * @Fields debtor_id:借款id
	 */
	private Integer debtorId;
	
	/**
	 * @Fields money:借款金额
	 */
	private Long money;
	
	/**
	 * @Fields lender:出借人
	 */
	private String lender;
	
	/**
	 * @Fields lender_id:出借人id
	 */
	private Integer lenderId;
	
	/**
	 * @Fields status:状态
	 */
	private Integer status;
	
	/**
	 * @Fields keyword 关键字
	 * */
	private Integer keyword;
	
	/**
	 * @Fields trade_type:交易类型
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
	 * @Fields creater_id:创建人id
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
	 * @Fields editor_id:修改人id
	 */
	private Integer editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	//columns END
}