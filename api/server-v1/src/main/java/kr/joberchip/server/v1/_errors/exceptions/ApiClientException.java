package kr.joberchip.server.v1._errors.exceptions;

import kr.joberchip.server.v1._utils.ApiResponse;
import org.springframework.http.HttpStatus;

public class ApiClientException extends RuntimeException {
  public ApiClientException(String field, String detailMessage) {
    super(field + " : " + detailMessage);
  }

  public ApiResponse.Result<Object> body() {
    return ApiResponse.error(getMessage(), status());
  }

  public HttpStatus status() {
    return HttpStatus.BAD_REQUEST;
  }
}
