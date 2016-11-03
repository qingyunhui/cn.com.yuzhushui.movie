package cn.com.yunzhushui.movie.apply;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import cn.com.yunzhushui.movie.constant.PropertyConstant;
import qing.yun.hui.common.enums.ICommonEnum;
import qing.yun.hui.common.enums.PropertiesEnum;
import qing.yun.hui.common.utils.EnumUtil;
import qing.yun.hui.common.utils.RSAUtil;
import qing.yun.hui.common.utils.StringUtil;

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
		 try {
			 resetData(props, PropertyConstant.PRIVATE_KEY);
           super.processProperties(beanFactoryToProcess, props);
       } catch (Exception e) {
           e.printStackTrace();
           throw new BeanInitializationException(e.getMessage());
       }
	}
	
	/**
	 * 对指定数据使用私钥进行解密
	 * @param props 加密的后Properties(源)
	 * @param privateKey 待解密的私钥
	 * */
	protected void resetData(Properties props,String privateKey) throws Exception{
		Object[] objs=EnumUtil.getEnumValues(PropertiesEnum.class.getName());
		if(null!=objs&& objs.length>0){
			for(Object obj:objs){
				ICommonEnum ico=(ICommonEnum) obj;
				String key=ico.getName();
				String encryptionData= props.getProperty(key);
				if(!StringUtil.isEmpty(encryptionData)){
					try {
						props.setProperty(key, RSAUtil.decryptionByPrivateKey(privateKey, encryptionData));
					} catch (Exception e) {
						//TODO 如果解密出现错误，那么忽略继续下一步..
						continue;
					}
				}
			}
		}
	}
}
