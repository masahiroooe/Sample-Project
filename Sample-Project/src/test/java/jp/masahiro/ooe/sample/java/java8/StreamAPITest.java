package jp.masahiro.ooe.sample.java.java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import jp.masahiro.ooe.sample.java.java8.bean.TestBean;
import jp.masahiro.ooe.sample.java.java8.bean.TestChildBean;

public class StreamAPITest {

	@Test
	public void testSumBigDecimal() throws Exception {
		TestChildBean chldBean = new TestChildBean();
		chldBean.setTestNumList(new ArrayList<BigDecimal>());

		chldBean.getTestNumList().add(BigDecimal.TEN);
		chldBean.getTestNumList().add(BigDecimal.ONE);
		chldBean.getTestNumList().add(BigDecimal.ONE);
		chldBean.getTestNumList().add(BigDecimal.ZERO);

		// リストの合計BigDecimalの場合
		BigDecimal sumTest = chldBean.getTestNumList().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

		// 何かの合計を一律加算した合計
		BigDecimal sumTest2 = chldBean.getTestNumList().stream().reduce(sumTest, BigDecimal::add);

		// 普通の数値の合計
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(10);
		intList.add(1);
		intList.add(1);
		intList.add(0);
		chldBean.setIntList(intList);

		TestBean testBean = new TestBean();
		testBean.getBeanList().add(chldBean);

		// TODO そのうち暇になったら続きは書く
		// 会員情報Beanから家族会員のリストの何かを合計したい。
		Map<String, TestChildBean> mapList = testBean.getBeanList().stream()
				.collect(Collectors.toMap(TestChildBean::getValueStr, Function.identity()));

	}

	@Test
	public void test1() throws Exception {
		List<String> testList = Arrays.asList("zzz", "aaa", "bbb", "ccc", "aaa", "bbb");

		System.out.println("-- START --\n");

		testList.stream().forEach(System.out::println);

		System.out.println("\n-- distinct --\n");

		List<String> returnList = testList.stream().distinct().collect(Collectors.toList());

		returnList.stream().forEach(System.out::println);

		System.out.println("\n-- sorted --\n");

		returnList.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);

	}

	@Test
	public void test2() throws Exception {
		List<String> testList = Arrays.asList("zzz", "aaa", "bbb", "ccc", "aaa", "bbb");

		List<String> returnList = testList.stream().sorted(Comparator.naturalOrder()).distinct()
				.collect(Collectors.toList());

		returnList.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);

	}

	private Function<String, TestBean> getTestBean = new Function<String, TestBean>() {
		@Override
		public TestBean apply(String arg0) {
			TestBean testBean = new TestBean();
			testBean.setId(arg0);
			testBean.setStrList(Arrays.asList("zzz", "aaa", "bbb", "ccc", "aaa", "bbb"));
			return testBean;
		}
	};

	private Function<List<String>, List<TestBean>> getTestBeanList = new Function<List<String>, List<TestBean>>() {

		@Override
		public List<TestBean> apply(List<String> t) {
			return t.stream().map(act -> getTestBean.apply(act)).collect(Collectors.toList());
		}

	};

	/**
	 * BeanのIDでリストを作成<br>
	 * リストはIDの重複なし、逆順ソート
	 */
	@Test
	public void test3() throws Exception {
		List<TestBean> testList = getTestBeanList.apply(Arrays.asList("zzz", "aaa", "bbb", "ccc", "aaa", "bbb"));
		List<String> retList = testList.stream().sorted(Comparator.comparing(TestBean::getId).reversed()).distinct()
				.map(map -> map.getId()).collect(Collectors.toList());

		retList.stream().forEach(System.out::println);

	}

}
