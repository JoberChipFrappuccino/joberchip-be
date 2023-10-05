package kr.joberchip.server.v1.user.controller;

import javax.validation.Valid;
import kr.joberchip.server.v1._config.security.CustomUserDetails;
import kr.joberchip.server.v1._utils.ApiResponse;
import kr.joberchip.server.v1.user.controller.dto.UpdateUserRequestDTO;
import kr.joberchip.server.v1.user.controller.dto.UpdateUserResponseDTO;
import kr.joberchip.server.v1.user.controller.dto.UserRequest;
import kr.joberchip.server.v1.user.service.UserService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/join")
  public ResponseEntity<?> join(@RequestBody @Valid UserRequest newUser, Errors errors) {
    userService.join(newUser);
    return ResponseEntity.ok().body(ApiResponse.success());
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid UserRequest loginUser, Errors error) {
    String token = userService.login(loginUser);
    return ResponseEntity.ok().header("Authorization", token).body(ApiResponse.success());
  }

  @PutMapping("/update")
  public ResponseEntity<ApiResponse.Result<UpdateUserResponseDTO>> updateUserInfo(
          @AuthenticationPrincipal CustomUserDetails loginUser,
          UpdateUserRequestDTO updateUserRequestDTO) {
    UpdateUserResponseDTO responseDTO = userService.updateUserInfo(loginUser, updateUserRequestDTO);

    return ResponseEntity.ok(ApiResponse.success(responseDTO));
  }
}
