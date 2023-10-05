package kr.joberchip.server.v1.space.controller;

import java.util.List;
import java.util.UUID;
import kr.joberchip.core.page.types.PrivilegeType;
import kr.joberchip.server.v1._config.security.CustomUserDetails;
import kr.joberchip.server.v1._utils.ApiResponse;
import kr.joberchip.server.v1.page.service.SharePagePrivilegeService;
import kr.joberchip.server.v1.page.service.SharePageService;
import kr.joberchip.server.v1.space.controller.dto.ParticipationInfoResponseDTO;
import kr.joberchip.server.v1.space.controller.dto.SpaceInviteRequestDTO;
import kr.joberchip.server.v1.space.service.SpaceParticipationInfoService;
import kr.joberchip.server.v1.space.service.SpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/space")
@RequiredArgsConstructor
public class SpaceController {
  private final SpaceService spaceService;
  private final SpaceParticipationInfoService spaceParticipationInfoService;
  private final SharePageService sharePageService;
  private final SharePagePrivilegeService sharePagePrivilegeService;

  @PostMapping("/new")
  public ApiResponse.Result<Object> createSpace(
      @AuthenticationPrincipal CustomUserDetails loginUser) {

    log.info("[SpaceController] loginUser : {}", loginUser);

    UUID defaultPageId = sharePageService.createDefaultPage(loginUser.user().getUserId());

    log.info("[SpaceController] Generated Default Page Id : {}", defaultPageId);

    sharePagePrivilegeService.registerPrivilege(
        loginUser.user().getUserId(), defaultPageId, PrivilegeType.EDIT);

    UUID generatedSpaceId = spaceService.createSpace(loginUser.user().getUserId(), defaultPageId);
    log.info("[SpaceController] Generated Space Id : {}", generatedSpaceId);

    spaceParticipationInfoService.registerOwnerInfo(loginUser.user().getUserId(), generatedSpaceId);

    return ApiResponse.success();
  }

  @GetMapping("/list")
  public ApiResponse.Result<List<ParticipationInfoResponseDTO>> participatingSpaces(
      @AuthenticationPrincipal CustomUserDetails loginUser) {

    log.info("[SpaceController] loginUser : {}", loginUser);

    List<ParticipationInfoResponseDTO> result =
        spaceService.getParticipatingInfo(loginUser.user().getUserId());

    return ApiResponse.success(result);
  }

  @DeleteMapping("/{spaceId}")
  public ApiResponse.Result<Object> deleteSpace(
      @AuthenticationPrincipal CustomUserDetails loginUser, @PathVariable UUID spaceId) {
    log.info("[SpaceController] loginUser : {}", loginUser);

    // Default Space 는 삭제 불가
    spaceParticipationInfoService.checkDefault(loginUser.user().getUserId(), spaceId);

    // Space Owner 가 아니면 삭제 불가
    spaceParticipationInfoService.checkOwner(loginUser.user().getUserId(), spaceId);

    // Space 삭제
    spaceService.deleteSpace(spaceId);

    // Space 참여 정보 삭제
    spaceParticipationInfoService.deleteParticipationInfo(loginUser.user().getUserId(), spaceId);

    return ApiResponse.success();
  }

  @PostMapping("/invite")
  public ApiResponse.Result<Object> invite(
      @AuthenticationPrincipal CustomUserDetails loginUser,
      @RequestBody SpaceInviteRequestDTO spaceInviteRequestDTO) {

    log.info("[SpaceController] loginUser : {}", loginUser);

    // 스페이스 소유자가 아니면 스페이스에 사용자 초애 불가
    spaceParticipationInfoService.checkOwnerOrDefault(
        loginUser.user().getUserId(), spaceInviteRequestDTO.spaceId());

    spaceParticipationInfoService.registerInvitation(spaceInviteRequestDTO);

    sharePagePrivilegeService.registerGivenPrivilegeForAllSpaceSubPage(
        spaceInviteRequestDTO, PrivilegeType.READ);

    return ApiResponse.success();
  }
}
