package kr.joberchip.server.v1.space.block.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLinkBlock {
    private String title;
    private String description;
    private String link;
}