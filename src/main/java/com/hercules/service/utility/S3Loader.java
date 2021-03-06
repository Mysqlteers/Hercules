package com.hercules.service.utility;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.awt.image.BufferedImage;
import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

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


    public static S3Loader getInstance() {
        if (instance == null)
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
            if(outputStream != null) {
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
        return url;
    }


    /**
     *This function upload a picture
     * @param fileName the name S3 bucket should save, with using iii/ will create a subfolder, with the name iii
     */
    public void uploadImage(File file, String fileName) {
        try {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretAccessKey);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion(region)
                    .build();

            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpg");

            request.setMetadata(metadata);
            s3Client.putObject(request);

        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }


    /**
     *This function upload a picture
     * @param fileName the name S3 bucket should save, using iii/ will create a subfolder, with the name iii
     */
    public void uploadFile(MultipartFile file, String fileName) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMetadata meta = new ObjectMetadata();
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, meta));
            inputStream.close();

        } catch (IOException | SdkClientException e) {
            e.printStackTrace();
        }
    }


    //convert multipartfile to normal file. Saves file in temp to get full file path
    public static File multipartFileToFile(MultipartFile multipartFile, String filename) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + filename);
        multipartFile.transferTo(convFile);
        return convFile;
    }
}