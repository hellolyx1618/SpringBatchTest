package com.lyx;

import org.junit.Test;
import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;

public class TestCaseDemo {

    public TestCaseDemo() {
        // TODO Auto-generated constructor stub
    }

    @Test
    public void test() {
        String[] tokens = new String[]{"foo", "1", "true"};
        FieldSet fs = new DefaultFieldSet(tokens);
        String name = fs.readString(0);
        int value = fs.readInt(1);
        boolean booleanValue = fs.readBoolean(2);

        System.out.println(name);
        System.out.println(value);
        System.out.println(booleanValue);
    }

}
