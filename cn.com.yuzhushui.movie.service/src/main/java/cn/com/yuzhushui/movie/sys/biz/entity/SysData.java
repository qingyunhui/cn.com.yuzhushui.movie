package cn.com.yuzhushui.movie.sys.biz.entity;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseModel;
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
public class SysData extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysData";
	
	//columns START
	/**
	 * @Fields dataId:dataId
	 */
	private Integer dataId;
	
	/**
	 * @Fields targetTable:targetTable
	 */
	private String targetTable;
	
	/**
	 * @Fields targetId:targetId
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
	 * @Fields createrId:createrId
	 */
	private Integer createrId;
	
	/**
	 * @Fields loseEfficacyDate:loseEfficacyDate
	 */
	private Date loseEfficacyDate;
	
	/**
	 * @Fields comment:comment
	 */
	private String comment;

	@Override
	public Integer getId() {
		return dataId;
	}
	
	//columns END

}