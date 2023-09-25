package kr.joberchip.server.v1.space.block.dto.create;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateVideoBlock {

    private String title;
    private String description;
    private MultipartFile videoFile;

}
