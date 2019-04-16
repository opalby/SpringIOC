package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import cn.mypro.bean.A;
import cn.mypro.bean.B;
import cn.mypro.config.Bean;
import cn.mypro.config.Property;
import cn.mypro.config.parse.ConfigManager;
import cn.mypro.main.BeanFactory;
import cn.mypro.main.ClassPathXmlApplicationContext;

public class test {

//	@Test
//	public void fun1() {
//		Map<String,Bean> map=ConfigManager.getConfig("/applicationContext.xml");
//		
//		for(Entry ss:map.entrySet()) {
//			System.out.println(ss.getKey()+"+"+(ss.getValue() instanceof Bean));
//			List<Property> m=((Bean) ss.getValue()).getProps();
//			if(m==null)
//				System.out.println("hello");
//			for(Property prop:m) {
//				System.out.println(prop.getName()+":"+prop.getValue());
//			}
//		}
//	}
	
	@Test
	public void fun2() {
		BeanFactory bf=new ClassPathXmlApplicationContext();
		B a=(B) bf.getBean("B");
		B a1=(B) bf.getBean("B");
		B a2=(B) bf.getBean("B");
		
		System.out.println(a.getA().getName());
	}

}
