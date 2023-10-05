package kr.joberchip.server.v1.block.controller.dto;

import kr.joberchip.core.block.LinkBlock;

public record LinkBlockDTO(
    String title, String link, Integer x, Integer y, Integer w, Integer h, Boolean visible) {

  public LinkBlock toEntity() {
    return LinkBlock.of(title, link, x, y, w, h);
  }
}
