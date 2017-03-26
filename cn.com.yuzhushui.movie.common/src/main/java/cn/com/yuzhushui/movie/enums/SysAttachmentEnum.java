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
	
	/**附件所在远程服务器的一级目录*/
	public static final String REMOTE_ONE_LEVEL_PATH="/mnt";
	
	@Getter
	public enum HANDLE_TYPE implements ICommonEnum{
		//'处理类型(缩略处理/截取处理/无处理)',
		UN_HANDLE(0,"un_handle","无处理"),
		THUMBNAIL_HANDLE(1,"thumbnail_handle","缩略图处理"),
		SCREENSHOT_HANDLE(2,"screenshot_handle","截图处理");
		
		private final int value;
		private final String code;
	    private final String name;
	    
	    private HANDLE_TYPE(int value,String code, String name) {
	        this.value = value;
	        this.code=code;
	        this.name = name;
	    }
	    
	    public static String getCodeByValue(int value){
	    	HANDLE_TYPE[] enums= HANDLE_TYPE.values();
			for(HANDLE_TYPE em:enums){
				if(em.getValue()==value){
					return em.getCode();
				}
			}
			return null;
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
		PHOTO_ALBUM(0,"photo_album","相册",6,640,320),
		MUSIC(1,"music","音乐",6,0,0),
		VIDEO(3,"video","视频",30,0,0),
		HEAD_PORTRAIT (3,"head_portrait","头像",10,0,0);
		
		private final int value;
		private final String code;
	    private final String name;
	    /**附件大小：单位MB*/
	    private final int size;
	    
	    /**宽*/
	    private final int width;
	    
	    /**高*/
	    private final int height;
	    
	    private CLASSIFY(int value,String code, String name,int size,int width,int height) {
	        this.value = value;
	        this.name = name;
	        this.code=code;
	        this.size=size;
	        this.width=width;
	        this.height=height;
	    }
		
	    public static Integer getValeByCode(String code){
			CLASSIFY classify= getClassifyByCode(code);
			if(null==classify) return null;
			return classify.getValue();
		}
	    
		public static Integer getCodeBySize(String code){
			CLASSIFY classify= getClassifyByCode(code);
			if(null==classify) return null;
			return classify.getSize();
		}
		
		public static CLASSIFY  getClassifyByCode(String code){
			CLASSIFY[] classifys= CLASSIFY.values();
			for(CLASSIFY ienum:classifys){
				if(ienum.getCode().equals(code)){
					return ienum;
				}
			}
			return null;
		 }
	}
	
	@Getter
	public enum IS_SYSTEM implements ICommonEnum{
		//是否系统内置(0:系统,1:用户)
		SYSTEM(0,"系统","system"),
		USER (1,"用户","user");
		
		private final int value;
	    private final String name;
	    private final String code;
	    
	    private IS_SYSTEM(int value, String name,String code) {
	        this.value = value;
	        this.name = name;
	        this.code=code;
	    }
	}

}
