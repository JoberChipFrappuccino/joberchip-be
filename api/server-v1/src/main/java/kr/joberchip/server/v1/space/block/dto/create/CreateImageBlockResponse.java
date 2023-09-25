package kr.joberchip.server.v1.space.block.dto.create;

import kr.joberchip.core.space.block.ImageBlock;
import kr.joberchip.core.storage.ImageBlockFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CreateImageBlockResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class imageBlockResponse {
        private UUID id;
        private String title;
        private String description;
        private String path;

        public static imageBlockResponse of(ImageBlock imageBlock) {
            return new imageBlockResponse(
                    imageBlock.getObjectId(),
                    imageBlock.getTitle(),
                    imageBlock.getDescription(),
                    imageBlock.getImageBlockFile().getAttachedFile().getSavePath());
        }
    }
}
