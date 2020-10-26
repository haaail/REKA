package com.reka.api.resource;

import com.reka.api.domain.ResponseMessage;
import com.reka.api.domain.TestPojo;
import com.reka.api.domain.pojo.S3ObjectPojo;
import com.reka.api.service.S3Service;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class RekaResource {
    @Inject
    private S3Service s3Service;

    @Path("/heartbeat")
    @GET
    public Response getHeartbeat() {
        return Response
                .ok()
                .entity(ResponseMessage.HEARTBEAT_MESSAGE.toString())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @Path("/list/{bucket}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listObjects(@PathParam("bucket") String bucket) {
        List<S3Object> objects = s3Service.getObjectsList(bucket);
        List<S3ObjectPojo> pojos = new ArrayList<>();
        for (S3Object object: objects) {
            pojos.add(new S3ObjectPojo(object));
        }

        GenericEntity<List<S3ObjectPojo>> entity = new GenericEntity<List<S3ObjectPojo>>(pojos) {};

        return Response
                .ok(entity)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @Path("/get/{bucket}/{key}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @GET
    public Response getObject(@PathParam("bucket") String bucket,
                              @PathParam("key") String key) {
        ResponseBytes<GetObjectResponse> response = s3Service.getObject(key, bucket);
        byte[] data = response.asByteArray();

        return Response
                .ok()
                .entity(data)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @Path("/upload/{bucket}/{key}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response putObject(@PathParam("bucket") String bucket,
                            @PathParam("key") String key,
                            @FormDataParam("file") InputStream uploadedInputStream,
                            @FormDataParam("file")FormDataContentDisposition disposition) {
        try {
            byte[] fileBytes = uploadedInputStream.readAllBytes();
            s3Service.putObject(key, bucket, fileBytes);

            return Response
                    .ok()
                    .entity(ResponseMessage.FILE_SUCCESSFULLY_UPLOADED)
                    .build();
        } catch (IOException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ResponseMessage.FILE_NOT_FOUND_MESSAGE)
                    .build();
        }
    }
}
