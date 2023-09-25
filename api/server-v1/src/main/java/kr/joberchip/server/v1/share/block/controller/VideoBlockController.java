package kr.joberchip.server.v1.share.block.controller;

import kr.joberchip.server.v1._utils.ApiResponse;
import kr.joberchip.server.v1.space.block.dto.create.CreateVideoBlock;
import kr.joberchip.server.v1.space.block.service.VideoBlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/space/{spaceId}/page/{pageId}/block")
public class VideoBlockController {

  private final VideoBlockService videoBlockService;

  @PostMapping("/video")
  public ApiResponse.Result<Object> createVideoBlock(
      @PathVariable Long spaceId,
      @PathVariable Long pageId,
      @RequestParam MultipartFile videoFile) {

    CreateVideoBlock createVideoBlock = new CreateVideoBlock();
    createVideoBlock.setVideoFile(videoFile);

    videoBlockService.uploadVideo(createVideoBlock);

    return ApiResponse.success();
  }

  @PutMapping("/video/{blockId}")
  public ApiResponse.Result<Object> modifyVideoBlock(
      @PathVariable Long spaceId,
      @PathVariable Long pageId,
      @PathVariable Long blockId,
      @RequestBody MultipartFile file) {

    return ApiResponse.success();
  }
}
