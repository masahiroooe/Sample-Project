package jp.masahiro.ooe.sample.fasterxml;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

@SuppressWarnings("all")
public class ObjectMapperTest {

	@Test
	public void testNullToBlankSelializer() throws Exception {

		CstObjectMapper om = new CstObjectMapper();

		TestBean testBean = new TestBean();

		System.out.println(om.writeValueAsString(testBean, TestBean.class));

		Predicate<Field> testType = t -> t.getType().getSimpleName().equals("Integer");

		System.out.println(om.writeValueAsString(testBean, TestBean.class, testType));

		testType = new Predicate<Field>() {

			@Override
			public boolean test(Field t) {

				return t.getType().getSimpleName().equals("String") || t.getType().getSimpleName().equals("Integer");
			}

		};

		System.out.println(om.writeValueAsString(testBean, TestBean.class, testType));

	}

	class CstObjectMapper extends ObjectMapper {

		private Null2BlankSerializer nullSerializer = new Null2BlankSerializer();

		public CstObjectMapper() {

			super();
			DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
			sp.setNullValueSerializer(nullSerializer);
			this.setSerializerProvider(sp);
		}

		public String writeValueAsString(Object value, Class<?> clazz) throws JsonProcessingException {
			nullSerializer.setTargetClazz(clazz);

			return super.writeValueAsString(value);
		}

		public String writeValueAsString(Object value, Class<?> clazz, Predicate<Field> testType)
				throws JsonProcessingException {
			nullSerializer.setTargetClazz(clazz);
			nullSerializer.setTestType(testType);

			return super.writeValueAsString(value);
		}

		private class Null2BlankSerializer extends JsonSerializer<Object> {

			private Class<?> targetClazz;

			private Predicate<Field> testType = t -> t.getType().getSimpleName().equals("String");

			public Null2BlankSerializer() {
				super();
			}

			@Override
			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

				Predicate<Field> testName = t -> t.getName().equals(jgen.getOutputContext().getCurrentName());

				if (Arrays.asList(targetClazz.getDeclaredFields()).stream().filter(testType.and(testName)).findFirst()
						.isPresent()) {
					jgen.writeString("");
				} else {
					jgen.writeObject(value);
				}

			}

			public Class<?> getTargetClazz() {
				return targetClazz;
			}

			public void setTargetClazz(Class<?> clazz) {
				this.targetClazz = clazz;
			}

			public Predicate<Field> getTestType() {
				return testType;
			}

			public void setTestType(Predicate<Field> testType) {
				this.testType = testType;
			}

		}

	}

	private class TestBean {

		private String name = null;
		private Integer id = null;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

	}

}
