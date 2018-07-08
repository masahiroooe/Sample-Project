package jp.masahiro.ooe.sample.java.java8.bean;

import java.math.BigDecimal;
import java.util.List;

public class TestChildBean {

	private String valueStr;

	private Integer valueInt;

	private List<String> valueListStr;

	private BigDecimal testNum;

	private List<BigDecimal> testNumList;

	private List<Integer> intList;

	public String getValueStr() {
		return valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

	public Integer getValueInt() {
		return valueInt;
	}

	public void setValueInt(Integer valueInt) {
		this.valueInt = valueInt;
	}

	public List<String> getValueListStr() {
		return valueListStr;
	}

	public void setValueListStr(List<String> valueListStr) {
		this.valueListStr = valueListStr;
	}

	public BigDecimal getTestNum() {
		return testNum;
	}

	public void setTestNum(BigDecimal testNum) {
		this.testNum = testNum;
	}

	public List<BigDecimal> getTestNumList() {
		return testNumList;
	}

	public void setTestNumList(List<BigDecimal> testNumList) {
		this.testNumList = testNumList;
	}

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}
}
