package jp.masahiro.ooe.sample.java.java8.bean;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class TestChildBean {

	private String valueStr;

	private Integer valueInt;

	private List<String> valueListStr;

	private BigDecimal testNum;

	private List<BigDecimal> testNumList;

	private List<Integer> intList;
}
