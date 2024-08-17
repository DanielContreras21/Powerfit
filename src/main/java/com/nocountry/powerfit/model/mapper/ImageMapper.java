package com.nocountry.powerfit.model.mapper;

import com.nocountry.powerfit.model.entity.Image;
import com.nocountry.powerfit.model.response.ImageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {
    public ImageResponse imageToDto(Image img){
        return ImageResponse.builder()
                .id(img.getId())
                .name(img.getFileName())
                .fileUrl(img.getImageUrl())
                .build();
    }
    public Image updateImageMapper(Image img, Image newImage){
        img.setImageUrl(newImage.getImageUrl());
        img.setFileName(newImage.getFileName());
        return img;
    }

    public List<ImageResponse> mapToDtoImagesList(List<Image> carrousel) {
        return carrousel.stream()
                .map(this::imageToDto)
                .collect(Collectors.toList());
    }
}