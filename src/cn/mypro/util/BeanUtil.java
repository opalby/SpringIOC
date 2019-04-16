package cn.mypro.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanUtil {

	public static Method getWriteMethod(Object beanObj,String name) {
		Method method=null;
		try {
			BeanInfo beanInfo= Introspector.getBeanInfo(beanObj.getClass());
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			
			if(pds!=null) {
				for(PropertyDescriptor pd:pds) {
					if(pd.getName().equals(name)) {
						method=pd.getWriteMethod();
					}
				}
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		if(method==null)
			throw new RuntimeException(" Ù–‘≤ª¥Ê‘⁄");
		return method;
	}
    
}
