package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:37:52
 * @history
 */
@Getter
@Setter
public class SysDataForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields data_id:dataId
	 */
	private Integer dataId;
	
	/**
	 * @Fields target_table:targetTable
	 */
	private String targetTable;
	
	/**
	 * @Fields target_id:targetId
	 */
	private Integer targetId;
	
	/**
	 * @Fields ctime:ctime
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:creater
	 */
	private String creater;
	
	/**
	 * @Fields creater_id:createrId
	 */
	private Integer createrId;
	
	/**
	 * @Fields lose_efficacy_date:loseEfficacyDate
	 */
	private Date loseEfficacyDate;
	
	/**
	 * @Fields comment:comment
	 */
	private String comment;
	//columns END
}