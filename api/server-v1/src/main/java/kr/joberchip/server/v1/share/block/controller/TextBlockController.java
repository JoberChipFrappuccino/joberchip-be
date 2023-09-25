package kr.joberchip.server.v1.share.block.controller;

import kr.joberchip.core.space.block.TextBlock;
import kr.joberchip.server.v1._utils.ApiResponse;
import kr.joberchip.server.v1.space.block.service.TextBlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/space/{spaceId}/page/{pageId}/block")
public class TextBlockController {

  private final TextBlockService textBlockService;

  @PostMapping("/text")
  public ApiResponse.Result<Object> createTextBlock(
      @PathVariable Long spaceId,
      @PathVariable Long pageId,
      @RequestBody String content) {

    TextBlock textBlock = textBlockService.createTextBlock(content);

    return ApiResponse.success(textBlock);
  }

  @PutMapping("/text/{blockId}")
  public ApiResponse.Result<Object> modifyTextBlock(
      @PathVariable Long spaceId,
      @PathVariable Long pageId,
      @PathVariable Long blockId,
      @RequestBody String content) {

    return ApiResponse.success();
  }
}
