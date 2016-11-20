package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
 * @history
 */
@Getter
@Setter
public class SysAttachmentForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields target_table:目标表
	 */
	private String targetTable;
	
	/**
	 * @Fields target_field:目标字段
	 */
	private String targetField;
	
	/**
	 * @Fields target_id:目标记录ID
	 */
	private String targetId;
	
	/**
	 * @Fields attachment_type:附件类型(附件类型:(text/html,image/jpeg等)
	 */
	private String attachmentType;
	
	/**
	 * @Fields classify:分类(相册、音乐、头像、视频)
	 */
	private Integer classify;
	
	/**
	 * @Fields url:访问的相对或绝对路径url
	 */
	private String url;
	
	/**
	 * @Fields comments:描述
	 */
	private String comments;
	
	/**
	 * @Fields physical_path:文件在磁盘上所在的绝对路径
	 */
	private String physicalPath;
	
	/**
	 * @Fields origin_name:文件名称
	 */
	private String originName;
	
	/**
	 * @Fields suffix:后缀
	 */
	private String suffix;
	
	/**
	 * @Fields size:文件大小
	 */
	private Long size;
	
	/**
	 * @Fields data:数据(转换二进制文件)
	 */
	private byte[] data;
	
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
	//columns END
}