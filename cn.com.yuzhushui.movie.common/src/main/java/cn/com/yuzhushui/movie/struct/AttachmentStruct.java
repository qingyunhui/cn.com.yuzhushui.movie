package cn.com.yuzhushui.movie.struct;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月26日下午1:32:28
 **/
@Getter
@Setter
public class AttachmentStruct {

	/**文件落地的磁盘路径:mnt/项目名/*/
	private String saveFilePath;
	
	private int x;
	
	private int y;
	
	private int width;
	
	private int height;
	
	public AttachmentStruct(){}
	
	public AttachmentStruct(String saveFilePath,int x,int y,int width,int height){
		this.saveFilePath=saveFilePath;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	
}
