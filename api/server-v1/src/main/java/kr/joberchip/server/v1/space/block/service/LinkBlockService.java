package kr.joberchip.server.v1.space.block.service;

import kr.joberchip.core.space.block.LinkBlock;
import kr.joberchip.server.v1.space.block.repository.LinkBlockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkBlockService {
    private final LinkBlockRepository linkBlockRepository;

    @Transactional
    public LinkBlock createLinkBlock(String title, String description, String link) {
        try {
            LinkBlock linkBlock = LinkBlock.builder()
                    .title(title)
                    .description(description)
                    .link(link)
                    .build();
            return linkBlockRepository.save(linkBlock);
        } catch (Exception e) {
            // 예외 처리 로직 추가
            log.error("Error while creating LinkBlock: " + e.getMessage(), e);
            throw new RuntimeException("Failed to create LinkBlock", e);
        }
    }


}
