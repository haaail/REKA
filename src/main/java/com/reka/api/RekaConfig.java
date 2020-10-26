package com.reka.api;

import com.reka.api.resource.RekaResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RekaConfig extends ResourceConfig {
    public RekaConfig() {
        // adds the RekaBinder to the main application for dependency binding
        register(new RekaBinder());
        // adds the RekaResource to the main application
        register(new RekaResource());
        // allow using multipart form data for uploads
        register(MultiPartFeature.class);
    }
//    @Override
//    public Set<Class<?>> () {
//        final Set<Class<?>> classes = new HashSet<>();
//        classes.add(RekaFeature.class);
//        classes.add(RekaResource.class);
//        return classes;
//    }
}
