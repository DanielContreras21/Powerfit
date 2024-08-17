package com.nocountry.powerfit.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nocountry.powerfit.model.entity.Image;
import com.nocountry.powerfit.service.abstraction.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class AwsServiceImpl implements AwsService {

    @Autowired
    private AmazonS3 amazonAwsConfig;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private String onlyFileName = "";

    @Override
    public Image uploadFile(MultipartFile file) {
        String newFileName = nameNewFile(file, converterFile(file), true);
        String nameImage = nameNewFile(file, converterFile(file), false);
        try {
            amazonAwsConfig.putObject(new PutObjectRequest(bucketName, newFileName, converterFile(file))
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            converterFile(file).delete();
        } catch (AmazonServiceException e) {
            converterFile(file).delete();
            throw new AmazonServiceException("Error: No se cargo el archivo en aws" + e.getMessage());
        }
        return Image.builder()
                .fileName(nameImage)
                .imageUrl(amazonAwsConfig.getUrl(bucketName, newFileName).toString())
                .build();
    }

    public String nameNewFile(MultipartFile file, File newfile, boolean only) {
        String newFileName;
        try (FileOutputStream stream = new FileOutputStream(newfile)) {
            stream.write(file.getBytes());
            if (only) newFileName = System.currentTimeMillis() + "_" + newfile.getName();
            else newFileName = newfile.getName();
        } catch (IOException e) {
            throw new RuntimeException("Error: Conversion de archivo" + e.getMessage());
        }
        return newFileName;
    }

    public File converterFile(MultipartFile file) {
        return new File(Objects.requireNonNull(file.getOriginalFilename()));
    }
}
