package cn.mypro.config.parse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.mypro.config.Bean;
import cn.mypro.config.Property;

public class ConfigManager {
	public static Map<String,Bean> getConfig(String s) {
		Map<String,Bean> map=new HashMap<String, Bean>();
		//注册解析器
		SAXReader reader=new SAXReader();
		//获取输入文件流,document
		InputStream is=ConfigManager.class.getResourceAsStream(s);
		Document doc=null;
		try {
			doc=reader.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		String xpath="//bean";
		
		List<Element> childEls=doc.selectNodes(xpath);
		
		if(childEls!=null) {
			for(Element childEl:childEls) {
				String name= childEl.attributeValue("name");
				String classname=childEl.attributeValue("class");
				String scope=childEl.attributeValue("scope");
				
				Bean bean=new Bean();	
				
				bean.setName(name);
				bean.setClassname(classname);
				if(scope!=null) {
					bean.setScope(scope);
				}
				
				List<Element> pops=childEl.elements("property");
				if(pops!=null) {
					for(Element pop:pops) {
						String pname= pop.attributeValue("name");
						String value=pop.attributeValue("value");
						String ref=pop.attributeValue("ref");
						
						Property prop=new Property();				
						prop.setName(pname);
						prop.setValue(value);
						prop.setRef(ref);
						
						bean.getProps().add(prop);
					}
				}
				map.put(name, bean);
			}
		}
		
		return map;
	}
}
