package cn.com.yunzhushui.movie.apply;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年10月26日上午9:52:56
 **/
public class CipherPropertyConfigurer extends PropertyPlaceholderConfigurer{
	
	//继承PropertyPlaceholderConfigurer类重写processProperties方法>>>>处理逻辑.....
	
	//继承PropertyPlaceholderConfigurer类重写convertProperty方法..>>>>
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)throws BeansException {
		/* try {
           String username = props.getProperty(PropertyConstant.JDBC_DRUID_USER);
           if (username != null) {
               props.setProperty(PropertyConstant.JDBC_DRUID_USER, DesEncrypt.Decrypt(username, DesEncrypt.hex2byte(key)));
           }
           
           String password = props.getProperty(PropertyConstant.JDBC_DRUID_PASSWORD);
           if (password != null) {
               props.setProperty(PropertyConstant.JDBC_DRUID_PASSWORD, DesEncrypt.Decrypt(password, DesEncrypt.hex2byte(key)));
           }
           
           String url = props.getProperty(PropertyConstant.JDBC_DRUID_URL);
           if (url != null) {
               props.setProperty(PropertyConstant.JDBC_DRUID_URL, DesEncrypt.Decrypt(url, DesEncrypt.hex2byte(key)));
           }
           super.processProperties(beanFactoryToProcess, props);
       } catch (Exception e) {
           e.printStackTrace();
           throw new BeanInitializationException(e.getMessage());
       }*/
	}
}
