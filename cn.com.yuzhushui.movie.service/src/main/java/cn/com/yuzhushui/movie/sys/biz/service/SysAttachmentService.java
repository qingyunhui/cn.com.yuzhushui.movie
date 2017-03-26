package cn.com.yuzhushui.movie.sys.biz.service;

import org.springframework.web.multipart.MultipartFile;

import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.struct.AttachmentStruct;
import cn.com.yuzhushui.movie.struct.ExtrasStruct;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAttachment;
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
 * @history
 */
public interface SysAttachmentService extends BaseService<SysAttachment,String>{
	
	/**
	 * <p>保存附件到数据库，且文件落地磁盘上</p>
	 * @param file
	 * @param extras
	 * @return boolean
	 * */
	public boolean saveAttachmentWithFileLanding(MultipartFile file,ExtrasStruct extras,AttachmentStruct attachmentStruct) throws Exception;
    
}