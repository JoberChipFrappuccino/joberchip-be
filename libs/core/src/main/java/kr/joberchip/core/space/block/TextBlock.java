package kr.joberchip.core.space.block;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import kr.joberchip.core.space.BaseObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "text_block_tb")
public class TextBlock extends BaseObject {
  @Column(name = "content")
  private String content;

  public UUID getTextBlockId() {
    return this.objectId;
  }
}
