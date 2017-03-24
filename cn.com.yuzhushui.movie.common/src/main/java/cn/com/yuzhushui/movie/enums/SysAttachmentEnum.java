package cn.com.yuzhushui.movie.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月24日下午10:45:49
 **/
public class SysAttachmentEnum {
	
	
	public static final String DEFAULT="default";
	
	@Getter
	public enum HANDLE_TYPE implements ICommonEnum{
		//'处理类型(缩略处理/截取处理/无处理)',
		UN_HANDLE(0,"无处理"),
		THUMBNAIL_HANDLE(1,"缩略图处理"),
		SCREENSHOT_HANDLE(2,"截图处理");
		
		private final int value;
	    private final String name;
	    
	    private HANDLE_TYPE(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum ATTACHMENT_TYPE implements ICommonEnum{
		//'附件类型(BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif等)',
		BMP(0,"BMP"),
		WBMP(1,"WBMP"),
		JPEG(2,"JPEG"),
		PNG(3,"PNG"),
		GIF(4,"GIF"),
		JPG(5,"JPG"),
		TXT(6,"TXT"),
		AVI(7,"AVI"),
		MP4(8,"MP4"),
		MPEG(9,"MPEG"),
		MPEG1(10,"MPEG-1"),
		MPEG2(11,"MPEG-2"),
		RM(12,"RM"),
		TS(13,"TS"),
		ASF(14,"ASF"),
		WMV(15,"WMV"),
		MOV(16,"MOV"),
		FLV(17,"FLV"),
		MPG(18,"MPG"),
		MP3(19,"MP3"),
		MKV(20,"MKV");
		
		private final int value;
	    private final String name;
	    
	    private ATTACHMENT_TYPE(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum CLASSIFY implements ICommonEnum{
		//'分类(相册、音乐、头像、视频)',
		PHOTO_ALBUM(0,"相册"),
		MUSIC(1,"音乐"),
		VIDEO(3,"视频"),
		HEAD_PORTRAIT (3,"头像");
		
		private final int value;
	    private final String name;
	    
	    private CLASSIFY(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum IS_SYSTEM implements ICommonEnum{
		//是否系统内置(0:系统,1:用户)
		SYSTEM(0,"系统"),
		USER (1,"用户");
		
		private final int value;
	    private final String name;
	    
	    private IS_SYSTEM(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}

}
