package tobyspring.helloboot;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@UnitTest @interface FastUnitTest {
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Test @interface UnitTest {
}

class HelloServiceTest {
	@FastUnitTest
	void simpleHelloService() {
		// given
		SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);

		// when
		String result = helloService.sayHello("Test");

		// then
		assertThat(result).isEqualTo("Hello Test");
	}

	private static HelloRepository helloRepositoryStub = new HelloRepository() {
		@Override
		public Hello findHello(String name) {
			return null;
		}

		@Override
		public void increaseCount(String name) {

		}
	};

	@Test
	void helloDecorator() {
		// given
		HelloDecorator helloDecorator = new HelloDecorator(name -> name);
		// when
		String result = helloDecorator.sayHello("Test");

		// then
		assertThat(result).isEqualTo("*Test*");
	}
}
