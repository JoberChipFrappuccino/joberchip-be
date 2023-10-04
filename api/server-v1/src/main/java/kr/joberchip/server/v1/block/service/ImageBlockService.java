package kr.joberchip.server.v1.block.service;

import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import kr.joberchip.core.block.ImageBlock;
import kr.joberchip.core.page.SharePage;
import kr.joberchip.server.v1.block.controller.dto.BlockResponseDTO;
import kr.joberchip.server.v1.block.controller.dto.ImageBlockDTO;
import kr.joberchip.server.v1.block.repository.ImageBlockRepository;
import kr.joberchip.server.v1.page.repository.SharePageRepository;
import kr.joberchip.server.v1.storage.service.S3StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageBlockService {
  private final ImageBlockRepository imageBlockRepository;
  private final SharePageRepository sharePageRepository;
  private final S3StorageService s3StorageService;

  @Transactional
  public BlockResponseDTO createImageBlock(UUID pageId, ImageBlockDTO imageBlockDTO) {

    SharePage parentPage =
        sharePageRepository.findById(pageId).orElseThrow(EntityNotFoundException::new);

    ImageBlock imageBlock = imageBlockDTO.toEntity();
    String link = s3StorageService.store(imageBlockDTO.attachedImage());

    imageBlock.setImageLink(link);

    imageBlock = imageBlockRepository.save(imageBlock);

    parentPage.addImageBlock(imageBlock);

    return BlockResponseDTO.fromEntity(imageBlock);
  }

  public BlockResponseDTO modifyImageBlock(UUID pageId, UUID blockId, ImageBlockDTO imageBlockDTO) {

    ImageBlock imageBlock =
        imageBlockRepository.findByObjectId(blockId).orElseThrow(EntityNotFoundException::new);

    if (imageBlockDTO.title() != null) imageBlock.setTitle(imageBlockDTO.title());
    if (imageBlockDTO.description() != null) imageBlock.setTitle(imageBlockDTO.description());
    if (imageBlockDTO.attachedImage() != null) {
      s3StorageService.delete(imageBlock.getImageLink());
      imageBlock.setImageLink(s3StorageService.store(imageBlockDTO.attachedImage()));
    }
    if (imageBlockDTO.visible() != null) {
      imageBlock.setVisible(imageBlockDTO.visible());
    }

    imageBlockRepository.save(imageBlock);

    return BlockResponseDTO.fromEntity(imageBlock);
  }

  public void deleteImageBlock(UUID pageId, UUID blockId) {
    SharePage sharePage =
        sharePageRepository
            .findSharePageByObjectId(pageId)
            .orElseThrow(EntityNotFoundException::new);

    ImageBlock imageBlock = imageBlockRepository.findByObjectId(blockId).orElseThrow(EntityNotFoundException::new);
    s3StorageService.delete(imageBlock.getImageLink());

    imageBlockRepository.deleteById(blockId);
    sharePage.getImageBlocks().remove(imageBlock);
  }
}
