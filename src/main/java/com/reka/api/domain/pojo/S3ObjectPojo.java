package com.reka.api.domain.pojo;

import software.amazon.awssdk.services.s3.model.S3Object;

import javax.xml.bind.annotation.XmlRootElement;

// POJO class created for JAXB to transform into XML to allow creating JAX RS response with the S3Object class information
@XmlRootElement
public class S3ObjectPojo {
    private String key;

    public S3ObjectPojo() {
    }

    public S3ObjectPojo(S3Object object) {
        this.key = object.key();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
