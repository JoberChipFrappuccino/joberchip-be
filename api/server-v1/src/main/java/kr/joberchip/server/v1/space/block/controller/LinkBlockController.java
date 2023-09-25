package kr.joberchip.server.v1.space.block.controller;

import kr.joberchip.core.space.block.LinkBlock;
import kr.joberchip.server.v1._utils.ApiResponse;
import kr.joberchip.server.v1.space.block.dto.create.CreateLinkBlock;
import kr.joberchip.server.v1.space.block.service.LinkBlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/space/{spaceId}/page/{pageId}/block")
public class LinkBlockController {
  private final LinkBlockService linkBlockService;

  @PostMapping("/link")
  public ApiResponse.Result<Object> createLinkBlock(
          @PathVariable Long spaceId,
          @PathVariable Long pageId,
          @RequestBody CreateLinkBlock createLinkBlock
  ) {
    String title = createLinkBlock.getTitle();
    String description = createLinkBlock.getDescription();
    String link = createLinkBlock.getLink();

    LinkBlock linkBlock = linkBlockService.createLinkBlock(title, description, link);

    return ApiResponse.success(linkBlock);
  }

  @PutMapping("/link/{blockId}")
  public ApiResponse.Result<Object> modifyLinkBlock(
      @PathVariable Long spaceId,
      @PathVariable Long pageId,
      @PathVariable Long blockId,
      @RequestBody CreateLinkBlock createLinkBlock) {

    return ApiResponse.success();
  }
}
