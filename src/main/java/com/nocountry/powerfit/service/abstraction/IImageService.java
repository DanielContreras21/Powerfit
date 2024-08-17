package com.nocountry.powerfit.service.abstraction;

import com.nocountry.powerfit.model.entity.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    List<Image> imagesPost(List<MultipartFile> postImage);

//    Image imageUser(MultipartFile image);
//    public void delete(Long id);
//    public Image update(Long id, Image img);
//    public MultipartFile userDefault() throws IOException;

}