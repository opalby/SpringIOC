package cn.mypro.config;

import java.util.ArrayList;
import java.util.List;

public class Bean {
	private String name;
	private String classname;
	private String scope="singleton";
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	private List<Property> props=new ArrayList<Property>();
	
	public List<Property> getProps() {
		return props;
	}
	public void setProps(List<Property> props) {
		this.props = props;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
