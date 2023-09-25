package kr.joberchip.core.space.block;

import javax.persistence.*;
import kr.joberchip.core.space.BaseObject;
import kr.joberchip.core.storage.VideoBlockFile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video_block_tb")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class VideoBlock extends BaseObject {
  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "video_link")
  private String videoLink;

  @OneToOne(mappedBy = "videoBlock")
  private VideoBlockFile videoFile;
}
