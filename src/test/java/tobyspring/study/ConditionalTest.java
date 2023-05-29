package tobyspring.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {
	@Test
	void conditional() {
		// true
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config1.class);
		context.refresh();

		MyBean bean = context.getBean(MyBean.class);

		// false
		AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext();
		context2.register(Config2.class);
		context2.refresh();

		MyBean bean2 = context2.getBean(MyBean.class);
	}

	@Configuration
	@Conditional(TrueCondition.class)
	static class Config1 {
		@Bean
		MyBean myBean() {
			return new MyBean();
		}
	}

	@Configuration
	@Conditional(FalseCondition.class)
	static class Config2 {
		@Bean
		MyBean myBean() {
			return new MyBean();
		}
	}

	private static class MyBean {
	}

	private static class TrueCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return true;
		}
	}
	private static class FalseCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return false;
		}
	}
}
