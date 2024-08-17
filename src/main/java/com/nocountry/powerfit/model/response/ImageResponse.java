package com.nocountry.powerfit.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {

    private  Long id;
    private String fileUrl;
    private String name;
}
