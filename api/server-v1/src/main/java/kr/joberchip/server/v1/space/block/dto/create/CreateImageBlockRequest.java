package kr.joberchip.server.v1.space.block.dto.create;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class CreateImageBlockRequest {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateImageBlockDTO {

        private String title;
        private String description;
        private MultipartFile attachedImage;

    }
}
