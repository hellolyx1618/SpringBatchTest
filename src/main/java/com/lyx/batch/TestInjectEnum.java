package com.lyx.batch;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyx.batch3.Gender;
import com.lyx.batch3.Worker;

public class TestInjectEnum {
	public static void main(String args[]) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring-enum.xml" });

		Worker worker = (Worker) context.getBean("worker");
		System.out.println(worker.getGender().toString());

		Worker worker1 = (Worker) context.getBean("worker1");
		System.out.println(worker1.getGender().toString());

		Map<Gender, Object> hello = (Map<Gender, Object>) context
				.getBean("map");
		System.out.println(hello.toString());

	}
}
