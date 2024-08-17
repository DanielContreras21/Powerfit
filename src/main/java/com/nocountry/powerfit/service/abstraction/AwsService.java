package com.nocountry.powerfit.service.abstraction;

import com.nocountry.powerfit.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface AwsService {
    Image uploadFile(MultipartFile m);
}
