package cn.com.yuzhushui.movie.sys.biz.service.impl;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.define.FileType;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.enums.SysAttachmentEnum;
import cn.com.yuzhushui.movie.struct.AttachmentStruct;
import cn.com.yuzhushui.movie.struct.ExtrasStruct;
import cn.com.yuzhushui.movie.sys.biz.dao.SysAttachmentDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAttachment;
import cn.com.yuzhushui.movie.sys.biz.service.SysAttachmentService;
import qing.yun.hui.common.utils.ImageUtil;
import qing.yun.hui.common.utils.StringUtil;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
 * @history
 */
@Service("sysAttachmentService")
public class SysAttachmentServiceImpl extends BaseServiceImpl<SysAttachment,String> implements SysAttachmentService{

	@Autowired
	private SysAttachmentDao sysAttachmentDao ;
	
	@Override
	public boolean saveAttachmentWithFileLanding(MultipartFile multipartFile, ExtrasStruct extras,AttachmentStruct attachmentStruct) throws Exception{
		boolean flag=false;
		if(null==multipartFile || null==extras || StringUtil.isEmpty(extras.getHandleType(),extras.getClassify(),extras.getIsSystem(),extras.getCreater())) return flag;
		if(null==attachmentStruct || StringUtil.isEmpty(attachmentStruct.getSaveFilePath())) return flag;
		String originFileName = multipartFile.getOriginalFilename();
		String suffix = FileType.getSuffixByFilename(originFileName);
		String id = UUID.randomUUID().toString();
		SysAttachment sysAttachment = new SysAttachment();
		sysAttachment.setId(id);
		sysAttachment.setOriginName(originFileName);
		sysAttachment.setSuffix(suffix);
		sysAttachment.setAttachmentType(multipartFile.getContentType());
		sysAttachment.setComments(multipartFile.getContentType());
		if(null!=extras){
			sysAttachment.setTargetTable(extras.getTargetTable());
			sysAttachment.setTargetField(extras.getTargetField());
			sysAttachment.setTargetId(extras.getTargetId());
			sysAttachment.setCreater(extras.getCreater());
			sysAttachment.setCreaterId(extras.getCreaterId());
			sysAttachment.setIsSystem(extras.getIsSystem());
			sysAttachment.setClassify(SysAttachmentEnum.CLASSIFY.getValeByCode(extras.getClassify()));
		}
		sysAttachment.setUrl("download.json?id=" + id);
		String separator=System.getProperty("file.separator");
		String saveFilePath=attachmentStruct.getSaveFilePath();	//文件落地的文件夹比如:(mnt目录或者data目录...)
		saveFilePath=saveFilePath.endsWith(separator)?saveFilePath:saveFilePath+separator;	//文件以等级目录出现比如:(mnt/或者data/)
		StringBuffer sourceSb=new StringBuffer();
		sourceSb.append(saveFilePath);//目录
		sourceSb.append(extras.getClassify()).append(separator);//分类
		//注意这里：判断添加的是系统默认级别还是用户级别的
		if(SysAttachmentEnum.IS_SYSTEM.SYSTEM.getValue()==extras.getIsSystem().intValue()){
			//系统级别的附件;（目录结构层次：目录/分类/系统级别/处理类型/图片）
			sourceSb.append(SysAttachmentEnum.IS_SYSTEM.SYSTEM.getCode()).append(separator);
		}else{
			//用户级别的附件;（目录结构层次：目录/分类/用户级别/账号/处理类型/图片）
			sourceSb.append(SysAttachmentEnum.IS_SYSTEM.USER.getCode()).append(separator);
			sourceSb.append(extras.getCreater()).append(separator);//账号
		}
		sourceSb.append(SysAttachmentEnum.HANDLE_TYPE.getCodeByValue(extras.getHandleType())).append(separator);//处理类型
		long size=0;
		try {
			//TODO 附件路径 ：源文件路径 (source，未裁剪 或者是未缩小的源文件)
			
			
			
			String targetPath=sourceSb.toString();//当前文件路径（目录/分类/【系统级别 or 用户级别】/处理类型）
			sourceSb.append("source").append(separator);
			//源文件目录结构层次：目录/分类/账号/处理类型/source/图片
			sourceSb.append(originFileName);
			String sourceFilePath=sourceSb.toString();		//源文件磁盘路径(未裁剪  or 未进行缩放的源附件)
			
			//TODO  end
			
			//分类型处理
			
			if(SysAttachmentEnum.HANDLE_TYPE.SCREENSHOT_HANDLE.getValue()==extras.getHandleType()){
				//先把源文件落地到磁盘上
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(sourceFilePath));
				//裁剪处理
				File files = new File(sourceFilePath);
				ImageUtil.cutImage(files, targetPath, new Rectangle(attachmentStruct.getX(), attachmentStruct.getY(), attachmentStruct.getWidth(), attachmentStruct.getHeight()));
				File targetFile=new File(targetPath);
				size=targetFile.length();
			}else if(SysAttachmentEnum.HANDLE_TYPE.THUMBNAIL_HANDLE.getValue()==extras.getHandleType()){
				//先把源文件落地到磁盘上
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(sourceFilePath));
				//缩略图处理
				ImageUtil.thumbnailImage(sourceFilePath, targetPath, attachmentStruct.getWidth(), attachmentStruct.getHeight());
				File targetFile=new File(targetPath);
				size=targetFile.length();
			}else{
				
				//TODO 
				
				//如果处理类型为，无处理的话，直接在指定文件下落地即可.
				size =multipartFile.getSize();
			}
			//TODO 
			sysAttachment.setSourceFilePath(sourceFilePath);
			targetPath=targetPath+originFileName;
			sysAttachment.setPhysicalPath(targetPath);
			//TODO end
			
			sysAttachment.setHandleType(extras.getHandleType());
			byte[] data=inputStreamByte(new FileInputStream(targetPath));
			sysAttachment.setData(data);
			sysAttachment.setSize(size);
			sysAttachmentDao.insert(sysAttachment);
			flag=true;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	
	/**  
     * InputStream 转为 byte  
     * @param InputStream  
     * @return 字节数组  
     * @throws Exception  
     */    
    public static byte[] inputStreamByte(InputStream inStream)throws Exception {    
        int count = 0;    
        while (count == 0) {    
            count = inStream.available();    
        }    
        byte[] b = new byte[count];    
        inStream.read(b);    
        return b;    
    }    
}