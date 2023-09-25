package kr.joberchip.server.v1.space.block.service;

import kr.joberchip.core.space.block.TextBlock;
import kr.joberchip.server.v1.space.block.repository.TextBlockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextBlockService {
    private final TextBlockRepository textBlockRepository;

    @Transactional
    public TextBlock createTextBlock(String content) {
        TextBlock textBlock = new TextBlock();
        textBlock.setContent(content);
        return textBlockRepository.save(textBlock);
    }


}
