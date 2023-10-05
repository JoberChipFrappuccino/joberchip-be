package kr.joberchip.server.v1.block.controller;

import java.util.UUID;

import kr.joberchip.server.v1._config.security.CustomUserDetails;
import kr.joberchip.server.v1._utils.ApiResponse;
import kr.joberchip.server.v1.block.controller.dto.BlockResponseDTO;
import kr.joberchip.server.v1.block.controller.dto.ImageBlockDTO;
import kr.joberchip.server.v1.block.service.ImageBlockService;
import kr.joberchip.server.v1.page.service.SharePagePrivilegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/page/{pageId}/imageBlock")
@RequiredArgsConstructor
public class ImageBlockController {
  private final ImageBlockService imageBlockService;
  private final SharePagePrivilegeService sharePagePrivilegeService;

  @PostMapping
  public ResponseEntity<ApiResponse.Result<BlockResponseDTO>> createImageBlock(
          @AuthenticationPrincipal CustomUserDetails loginUser,
          @PathVariable UUID pageId,
          @RequestBody ImageBlockDTO imageBlockDTO) {

    sharePagePrivilegeService.checkEditPrivilege(loginUser.user().getUserId(), pageId);
    BlockResponseDTO response = imageBlockService.createImageBlock(pageId, imageBlockDTO);

    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PutMapping("/{blockId}")
  public ResponseEntity<ApiResponse.Result<BlockResponseDTO>> modifyImageBlock(
          @AuthenticationPrincipal CustomUserDetails loginUser,
          @PathVariable UUID pageId,
          @PathVariable UUID blockId,
          @RequestBody ImageBlockDTO imageBlockDTO) {

    sharePagePrivilegeService.checkEditPrivilege(loginUser.user().getUserId(), pageId);
    BlockResponseDTO response = imageBlockService.modifyImageBlock(pageId, blockId, imageBlockDTO);

    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @DeleteMapping("/{blockId}")
  public ResponseEntity<ApiResponse.Result<Object>> deleteImageBlock(
          @AuthenticationPrincipal CustomUserDetails loginUser,
          @PathVariable UUID pageId,
          @PathVariable UUID blockId) {

    sharePagePrivilegeService.checkEditPrivilege(loginUser.user().getUserId(), pageId);
    imageBlockService.deleteImageBlock(pageId, blockId);

    return ResponseEntity.ok(ApiResponse.success());
  }
}
