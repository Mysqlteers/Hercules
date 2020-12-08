package com.hercules.service.utility;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class S3Loader {

    private static S3Loader instance;

    private Regions region = Regions.EU_CENTRAL_1;               // required
    private String accessKey = "AKIA4MEKDBVSKP76B52M";             // required
    private String secretAccessKey= "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";       // required
    private String bucketName= "hercules-storage-bucket";            // required
    private AmazonS3 s3Client;

    /**
     * Creates the aws client credentials, for interaction between application and aws s3 bucket
     */
    private S3Loader() {
        try {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretAccessKey);
            s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion(region)
                    .build();
        }
        catch (Exception e){e.printStackTrace();}
    }


    public static S3Loader getInstance()
    {
        if (instance==null)
            instance =  new S3Loader();
        return instance;
    }

    /**
     *
     * @param s3ObjectName the name of the file as located from the hercules bucket on aws, ask norsker.
     * @return the file.
     * @throws IOException from closing filestream.
     */
    public  File getS3Object(String s3ObjectName) throws IOException {
        File file = null;
        OutputStream outputStream = null;
        try {
            S3Object s3object = s3Client.getObject(bucketName, s3ObjectName);
            S3ObjectInputStream inputStream = s3object.getObjectContent();

            file = new File(s3ObjectName);
            outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            if(outputStream != null)
            {
                outputStream.close();
            }
        }

        return file;
    }


    public  String getS3ObjectUrl(String s3ObjectName) {
        String url ="";
        try {
             url = s3Client.getUrl(bucketName, s3ObjectName).toExternalForm();
        }
        catch (Exception e){e.printStackTrace();}
        finally {
        }

        return url;
    }


    /**
     *This function upload a picture
     * @param filepath the path on your local computer to the desired file.
     * @param fileName the name S3 bucket should save, with using / will create a subfolder, and the subfolder will not be public!
     */
    public void uploadImage(String filepath, String fileName) {
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