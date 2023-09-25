package kr.joberchip.server.v1.share.block.service;

import kr.joberchip.server.v1._errors.exceptions.StorageException;
import kr.joberchip.server.v1.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageBlockService {
  private final ImageBlockService imageBlockRepository;
  private final AttachedFileRepository attachedFileRepository;

/*
  public void store(MultipartFile file) {
    try {
      storageService.store(file);
    } catch (StorageException se) {
      log.info("StorageException : {}", se.getMessage());
      log.info("caused by: {}", se.getCause().getMessage());
    }
    log.info("Successfully uploaded : {}", file.getOriginalFilename());
  }
 */
  @Value("${file.dir}")
  private String fileDir;

  public String getFullPath(String filename) {
    return fileDir + filename;
  }

  @Transactional
  public ImageBlock createImageBlock(CreateImageBlockRequest.CreateImageBlockDTO createImageBlockDTO) throws IOException {
    ImageBlock imageBlock = ImageBlock.builder()
            .title(createImageBlockDTO.getTitle())
            .description(createImageBlockDTO.getDescription())
            .build();

    AttachedFile attachedFile = ImageFile(createImageBlockDTO.getAttachedImage());
    //imageBlock = imageBlockRepository.save(imageBlock);
    return imageBlock;
  }
  private AttachedFile ImageFile(MultipartFile multipartFile) throws IOException {

    String originalFilename = multipartFile.getOriginalFilename();
    String storeFileName = createStoreFileName(originalFilename);
    multipartFile.transferTo(new File(getFullPath(storeFileName)));
    return AttachedFile.of("image", storeFileName);
  }

  private String createStoreFileName(String originalFilename) {
    String ext = extractExt(originalFilename);
    String uuid = UUID.randomUUID().toString();
    return uuid + "." + ext;
  }

  private String extractExt(String originalFilename) {
    int pos = originalFilename.lastIndexOf(".");
    return originalFilename.substring(pos + 1);
  }
}
