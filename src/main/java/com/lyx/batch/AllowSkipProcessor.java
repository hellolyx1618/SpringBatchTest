package com.lyx.batch;

import org.springframework.batch.item.ItemProcessor;

public class AllowSkipProcessor implements ItemProcessor<People, PeopleDESC> {

    public PeopleDESC process(People item) throws Exception {
        System.out.println("process people desc");
        if ("lyx".equals(item.getFirstName())) {
            System.out.println("skip invalid data!!!!!!!!!!!!!!!!!!!!!!!!");
            throw new InvalidDataException("skip this data!!!!!!!!!!!!");
        }
        return new PeopleDESC(item.getLastName(), item.getFirstName(), Thread
                .currentThread().getName());
    }
}
