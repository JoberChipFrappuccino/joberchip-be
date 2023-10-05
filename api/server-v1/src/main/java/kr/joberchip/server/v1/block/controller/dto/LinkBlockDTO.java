package kr.joberchip.server.v1.block.controller.dto;

import kr.joberchip.core.block.LinkBlock;
import org.springframework.web.bind.annotation.RequestParam;

public record LinkBlockDTO(
    @RequestParam String title,
    @RequestParam String link,
    @RequestParam Integer x,
    @RequestParam Integer y,
    @RequestParam Integer w,
    @RequestParam Integer h,
    @RequestParam Boolean visible) {

  public LinkBlock toEntity() {
    return LinkBlock.of(title, link, x, y, w, h);
  }
}
