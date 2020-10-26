package com.reka.api;

import com.reka.api.service.S3Service;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RekaBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // bind S3Service for resource dependency injection
        bind(S3Service.class).to(S3Service.class);
    }
}
