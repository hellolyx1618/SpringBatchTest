package com.lyx.batch;

import org.springframework.batch.item.ItemProcessor;

public class ThrowExceptionProcessor implements
		ItemProcessor<People, PeopleDESC> {

	public PeopleDESC process(People item) throws Exception {
		System.out.println("process people desc");
		if ("lyx".equals(item.getFirstName())) {
			throw new InvalidDataException("invalid data");
		}
		return new PeopleDESC(item.getLastName(), item.getFirstName(), Thread
				.currentThread().getName());
	}
}