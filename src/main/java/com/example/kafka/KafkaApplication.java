package com.example.kafka;

import com.example.kafka.config.KafkaBindings;
import com.example.kafka.process.TestProcess;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binding.StreamListenerAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.stream.config.BindingServiceConfiguration.STREAM_LISTENER_ANNOTATION_BEAN_POST_PROCESSOR_NAME;

@SpringBootApplication
@EnableBinding(KafkaBindings.class)
public class KafkaApplication {

	@Bean(name = STREAM_LISTENER_ANNOTATION_BEAN_POST_PROCESSOR_NAME)
	public static BeanPostProcessor streamListenerAnnotationBeanPostProcessor() {
		return new StreamListenerAnnotationBeanPostProcessor() {
			@Override
			protected StreamListener postProcessAnnotation(StreamListener originalAnnotation,
														   Method annotatedMethod) {
				Map<String, Object> attributes = new HashMap<>(
						AnnotationUtils.getAnnotationAttributes(originalAnnotation));
				attributes.put("condition", "headers['type']=='" + originalAnnotation.condition() + "'");
				return AnnotationUtils.synthesizeAnnotation(attributes, StreamListener.class, annotatedMethod);
			}
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(TestProcess process){
		return e -> {
			process.start();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}
}
