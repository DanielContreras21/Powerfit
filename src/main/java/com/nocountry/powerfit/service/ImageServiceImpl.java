package com.nocountry.powerfit.service;

import com.nocountry.powerfit.model.entity.Image;
import com.nocountry.powerfit.repository.IImageRepository;
import com.nocountry.powerfit.service.abstraction.AwsService;
import com.nocountry.powerfit.service.abstraction.IImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class ImageServiceImpl implements IImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    @Autowired
    private  AwsService awsService;
    @Autowired
    private  IImageRepository iImageRepository;

    public Image findById(Long id) {
        return iImageRepository.findById(id.intValue()).orElseThrow();
    }

    @Override
    public List<Image> imagesPost(List<MultipartFile> files) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile m : files){

            images.add(iImageRepository.save(awsService.uploadFile(m)));
        }
        LOGGER.warn("Array de amazon creado "+images.size());
        return images;
    }
}
