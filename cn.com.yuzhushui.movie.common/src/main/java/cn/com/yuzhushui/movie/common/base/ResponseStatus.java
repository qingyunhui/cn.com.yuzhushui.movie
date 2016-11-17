package cn.com.yuzhushui.movie.common.base;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午10:40:38
 **/
public enum ResponseStatus {

	FAILED(0, "失败"), 
	SUCCESS(1, "成功");

	private final int value;
	private final String name;

	private ResponseStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static String getNameByValue(int ordinal) {
		for (ResponseStatus v : values()) {
			if (v.getValue() == ordinal) {
				return v.getName();
			}
		}
		return null;
	}
	
}
