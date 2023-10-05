package kr.joberchip.server.v1.block.controller;

import java.util.UUID;

import kr.joberchip.server.v1._config.security.CustomUserDetails;
import kr.joberchip.server.v1._utils.ApiResponse;
import kr.joberchip.server.v1.block.controller.dto.BlockResponseDTO;
import kr.joberchip.server.v1.block.controller.dto.LinkBlockRequestDTO;
import kr.joberchip.server.v1.block.service.LinkBlockService;
import kr.joberchip.server.v1.page.service.SharePagePrivilegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/page/{pageId}/linkBlock")
public class LinkBlockController {
  private final LinkBlockService linkBlockService;
  private final SharePagePrivilegeService sharePagePrivilegeService;

  @PostMapping
  public ResponseEntity<ApiResponse.Result<Object>> createLinkBlock(
      @AuthenticationPrincipal CustomUserDetails loginUser,
      @PathVariable UUID pageId,
      @RequestBody LinkBlockRequestDTO createLinkBlock) {

    sharePagePrivilegeService.checkEditPrivilege(loginUser.user().getUserId(), pageId);
    BlockResponseDTO responseDTO = linkBlockService.createLinkBlock(pageId, createLinkBlock);

    return ResponseEntity.ok(ApiResponse.success(responseDTO));
  }

  @PutMapping("/{blockId}")
  public ResponseEntity<ApiResponse.Result<Object>> modifyLinkBlock(
      @AuthenticationPrincipal CustomUserDetails loginUser,
      @PathVariable UUID pageId,
      @PathVariable UUID blockId,
      @RequestBody LinkBlockRequestDTO modifyRequestDTO) {

    sharePagePrivilegeService.checkEditPrivilege(loginUser.user().getUserId(), pageId);
    BlockResponseDTO responseDTO = linkBlockService.modifyLinkBlock(pageId, blockId, modifyRequestDTO);

    return ResponseEntity.ok(ApiResponse.success(responseDTO));
  }

  @DeleteMapping("/{blockId}")
  public ResponseEntity<ApiResponse.Result<Object>> deleteLinkBlock(
      @AuthenticationPrincipal CustomUserDetails loginUser,
      @PathVariable UUID pageId, @PathVariable UUID blockId) {

    sharePagePrivilegeService.checkEditPrivilege(loginUser.user().getUserId(), pageId);
    linkBlockService.deleteLinkBlock(pageId, blockId);

    return ResponseEntity.ok(ApiResponse.success());
  }
}
