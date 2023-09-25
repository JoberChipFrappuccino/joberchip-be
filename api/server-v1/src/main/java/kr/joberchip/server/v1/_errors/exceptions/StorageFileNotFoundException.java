package kr.joberchip.server.v1._errors.exceptions;

public class StorageFileNotFoundException extends StorageException {
  public StorageFileNotFoundException(String message) {
    super(message);
  }

  public StorageFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
