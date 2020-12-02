package com.hercules.service.utility;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PublicAccessBlockConfiguration;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

public class S3Uploader {


    public static void main(String[] args) throws IOException {
        new S3Uploader().uploadImage("src/main/resources/static/images/default-logo.png", "test/test/test/test.jpg");}

    Regions region = Regions.EU_CENTRAL_1;               // required
    String accessKey = "AKIA4MEKDBVSKP76B52M";             // required
    String secretAccessKey= "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";       // required
    String bucketName= "hercules-storage-bucket";            // required

    /**
     *This function upload a picture
     * @param filepath the path on your local computer to the desired file.
     * @param fileName the name S3 bucket should save, with using / will create a subfolder, and the subfolder will not be public!
     * @throws IOException
     */
    public void uploadImage(String filepath, String fileName) throws IOException {


        try {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretAccessKey);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion(region)
                    .build();

            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, new File(filepath));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpg");
            request.setMetadata(metadata);
            s3Client.putObject(request);

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}