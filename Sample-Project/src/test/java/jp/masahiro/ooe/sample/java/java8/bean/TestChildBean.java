package jp.masahiro.ooe.sample.java.java8.bean;

import java.util.List;

import lombok.Data;

@Data
public class TestChildBean {

	private String valueStr;

	private Integer valueInt;

	private List<String> valueListStr;

}
