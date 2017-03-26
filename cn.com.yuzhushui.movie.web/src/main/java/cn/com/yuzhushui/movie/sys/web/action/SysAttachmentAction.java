package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.SysAttachmentEnum;
import cn.com.yuzhushui.movie.enums.SysAttachmentEnum.CLASSIFY;
import cn.com.yuzhushui.movie.struct.AttachmentStruct;
import cn.com.yuzhushui.movie.struct.ExtrasStruct;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAttachment;
import cn.com.yuzhushui.movie.sys.biz.service.SysAttachmentService;
import cn.com.yuzhushui.movie.sys.web.vo.SysAttachmentForm;
import qing.yun.hui.common.utils.StringUtil;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
 * @history
 */
@Controller
@RequestMapping(SysAttachmentAction.ACTION_PATH)
public class SysAttachmentAction extends BaseAction<SysAttachment, SysAttachmentForm, String>{
	
	protected Logger logger=LoggerFactory.getLogger(SysAttachmentAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysAttachment";
	
	@Autowired
	private SysAttachmentService sysAttachmentService;
	
	/**
	 * @param attachments 附件
	 * @param redirectAttributes
	 * @param classify 分类(对应枚举类的code)
	 * @return 
	 * */
	@RequestMapping(value="upload", method = {RequestMethod.POST})
    public ModelAndView upload(MultipartFile attachments,RedirectAttributes redirectAttributes,String classify){
		if(StringUtil.isEmpty(classify) || null==SysAttachmentEnum.CLASSIFY.getValeByCode(classify)){
			redirectAttributes.addFlashAttribute(MovieConstant.MESSAGES_INFO, "上传失败！缺少必要参数。");
			return new ModelAndView("redirect:"+ACTION_PATH+"/userMain.htm");
		}
		if(null==attachments||attachments.getSize()<1){
			redirectAttributes.addFlashAttribute(MovieConstant.MESSAGES_INFO, "上传失败！请核对上传文件是否正确。");
			return new ModelAndView("redirect:"+ACTION_PATH+"/userMain.htm");
		}
		SysAccount account=SessionUtil.getSysAccount();
		ExtrasStruct extras = ExtrasStruct.getExtrasStruct(SysAccount.class, "account_id", account.getAccountId()+"");
		double uploadSize=StringUtil.getFileMb(attachments.getSize());
		if(uploadSize>SysAttachmentEnum.CLASSIFY.getCodeBySize(classify)){
			redirectAttributes.addFlashAttribute(MovieConstant.MESSAGES_INFO, "上传附件大小不能大于:"+SysAttachmentEnum.CLASSIFY.getCodeBySize(classify)+"MB。");
			return new ModelAndView("redirect:"+ACTION_PATH+"/userMain.htm");
		}
        extras.setCreaterId(account.getAccountId());
        extras.setCreater(account.getAccount());
        extras.setHandleType(SysAttachmentEnum.HANDLE_TYPE.THUMBNAIL_HANDLE.getValue());
        extras.setClassify(classify);
        extras.setIsSystem(SysAttachmentEnum.IS_SYSTEM.USER.getValue());
        //文件落地成功后，删除原始文件..
        CLASSIFY enums= SysAttachmentEnum.CLASSIFY.getClassifyByCode(classify);
        int width=0;
        int height=0;
        if(null!=enums){
        	width=enums.getWidth();
        	height=enums.getHeight();
        }
        AttachmentStruct attachmentStruct =new AttachmentStruct(SysAttachmentEnum.REMOTE_ONE_LEVEL_PATH,0,0,width,height);
        boolean success=Boolean.FALSE;
        try {
        	success=sysAttachmentService.saveAttachmentWithFileLanding(attachments, extras, attachmentStruct);
		} catch (Exception e) {
			logger.error("=====================>附件上传失败，失败原因:{}",JSONObject.toJSONString(e));
			e.printStackTrace();
		}
        redirectAttributes.addFlashAttribute(MovieConstant.MESSAGES_INFO, success?"上传失败！":"上传失败.");
        return new ModelAndView("redirect:"+ACTION_PATH+"/userMain.htm");
    }
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
}