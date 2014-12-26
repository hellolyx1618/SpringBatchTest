package com.lyx.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyx.batch3.Role;
import com.lyx.batch3.User;

/**
 * 测试bean 的scope
 * 
 * @author Lenovo
 *
 */
public class TestBeanScope {

	public static void main(String args[]) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring-bean-scope.xml" });

		User user = (User) context.getBean("user");
		System.out.println("user hashcode=" + user.hashCode());
		System.out.println("role hashcode=" + user.getRole().hashCode());

		Role role = (Role) context.getBean("role");
		System.out.println("direct role hashcode=" + role.hashCode());

	}
}
