package kr.joberchip.server.v1.space.block.dto.modify;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ModifyVideoBlock {

    private String title;
    private MultipartFile file;
}
