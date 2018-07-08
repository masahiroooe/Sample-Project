package jp.masahiro.ooe.sample.java.java8.bean;

import java.util.ArrayList;
import java.util.List;

public class TestBean {

	private String id;

	private List<String> strList = new ArrayList<String>();

	private List<TestChildBean> beanList = new ArrayList<TestChildBean>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public List<TestChildBean> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<TestChildBean> beanList) {
		this.beanList = beanList;
	}

}
