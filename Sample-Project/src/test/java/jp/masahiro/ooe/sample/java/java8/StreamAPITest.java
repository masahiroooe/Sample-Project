package jp.masahiro.ooe.sample.java.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

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
}
