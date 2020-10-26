package com.reka.api.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestPojo {
    String name;

    public TestPojo() {
    }

    public TestPojo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
