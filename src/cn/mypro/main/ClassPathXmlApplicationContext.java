package cn.mypro.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import cn.mypro.config.Bean;
import cn.mypro.config.Property;
import cn.mypro.config.parse.ConfigManager;
import cn.mypro.util.BeanUtil;

public class ClassPathXmlApplicationContext implements BeanFactory {
	private Map<String, Bean> config;
	private Map<String, Object> context=new HashMap<String, Object> ();

	public ClassPathXmlApplicationContext() {
		config = ConfigManager.getConfig("/applicationContext.xml");
		if (config != null) {
			for (Entry<String,Bean> en : config.entrySet()) {
				String beanName = en.getKey();
				//System.out.println(beanName);
				Bean bean=en.getValue();
				Object beanObj=context.get(beanName);
				if(beanObj==null && bean.getScope()=="singleton") {
					beanObj =createBean(bean);
					context.put(beanName, beanObj);
					//System.out.println(beanObj);
				}
			}
		}
	}

	private Object createBean(Bean bean) {
		String className = bean.getClassname();
		Class clazz=null;
		Object beanObj=null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			beanObj=clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean.getProps()!=null) {
			
			for(Property pro:bean.getProps()) {
				String proName=pro.getName();				
				Method method=BeanUtil.getWriteMethod(beanObj, proName);
				
				Object param=null;
				
				if(pro.getValue()!=null) {
					String value=pro.getValue();
					
					//Map<String, String[]> paramMap=new HashMap<String,String[]>();
//					paramMap.put(proName, new String[] {value});
//					try {
//						BeanUtils.populate(beanObj, paramMap);
//					} catch (IllegalAccessException | InvocationTargetException e) {
//						e.printStackTrace();
//					}
					param=value;
				}
				if(pro.getRef()!=null) {	
					String ref=pro.getRef();
					Object refObj=context.get(ref);
					if(refObj==null) {
						Bean proBean=config.get(ref);
						refObj=createBean(proBean);
						if(bean.getScope()=="singleton")
							context.put(ref, refObj);
					}
					param=refObj;
					
				}
				try {
					method.invoke(beanObj, param);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return beanObj;
	}

	@Override
	public Object getBean(String beanName) {		
		Object res=context.get(beanName);
		if(res==null) {
			res=createBean(config.get(beanName));
		}
		return res;
	}

}
