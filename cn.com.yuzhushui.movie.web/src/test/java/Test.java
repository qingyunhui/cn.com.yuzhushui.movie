import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.yuzhushui.movie.sys.biz.entity.SysOprationLog;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年2月10日上午10:58:33
 **/
public class Test {
	
	public static void main(String[] args){
		
		Long money=-500L;
		System.out.println(Math.abs(money));
		
		/*String device="mozilla/5.0 (linux; u; android 6.0.1; zh-cn; mi 5s build/mxb48t) applewebkit/537.36 (khtml, like gecko)version/4.0 chrome/37.0.0.0 mqqbrowser/7.3 mobile safari/537.36";
		
		Pattern pattern = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?(Build)?/");
		
		String str="Mozilla/5.0 (Linux; U; Android 4.2.2; zh-CN; HTC HTL22 Build/JDQ39) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/9.9.2.467 U3/0.8.0 Mobile Safari/533.1";	


		Matcher matcher = pattern.matcher(str);
		String model = null;
		if (matcher.find()) {
			model = matcher.group(1).trim();
			System.out.println("通过userAgent解析出机型：" + model);
		}*/
		
	}

}
