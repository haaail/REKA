package com.reka.api.service;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.List;


public class S3Service {
    private S3Client s3;

    public S3Service() {
        this.s3 = S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .build();
    }

    // utility method to get the specified bucket's objects list
    public List<S3Object> getObjectsList(String bucket) {
        List<S3Object> objects = null;
        try {
            ListObjectsRequest request = ListObjectsRequest
                    .builder()
                    .bucket(bucket)
                    .build();

            ListObjectsResponse response = s3.listObjects(request);
            objects = response.contents();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objects;
    }

    // utility method to get an object specified by key and bucket
    public ResponseBytes<GetObjectResponse> getObject(String key, String bucket) {
        ResponseBytes<GetObjectResponse> response = null;

        try {
            GetObjectRequest request = GetObjectRequest
                    .builder()
                    .key(key)
                    .bucket(bucket)
                    .build();

            response = s3.getObjectAsBytes(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    // utility method to put an object into the specified bucket
    public PutObjectResponse putObject(String key, String bucket, byte[] data) {
        PutObjectResponse response = null;

        try {
            PutObjectRequest request = PutObjectRequest
                    .builder()
                    .key(key)
                    .bucket(bucket)
                    .build();

            response = s3.putObject(request, RequestBody.fromBytes(data));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
