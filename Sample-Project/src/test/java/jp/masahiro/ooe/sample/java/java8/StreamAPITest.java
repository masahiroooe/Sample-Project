package jp.masahiro.ooe.sample.java.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import jp.masahiro.ooe.sample.java.java8.bean.TestBean;

public class StreamAPITest {

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
