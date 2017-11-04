package jp.masahiro.ooe.sample.java.java8.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TestBean {
	
	private String id;
	private List<String> strList = new ArrayList<String>();
	private List<TestChildBean> beanList = new ArrayList<TestChildBean>();
		

}
