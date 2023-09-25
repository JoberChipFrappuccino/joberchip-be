package kr.joberchip.core.space;

import java.util.UUID;
import javax.persistence.*;
import kr.joberchip.core.space.page.SpacePage;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@Getter
public abstract class BaseObject extends OnScreenLocation {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "object_id", columnDefinition = "BINARY(16)")
  private UUID objectId;

  @ManyToOne
  @JoinColumn(name = "page_id")
  private SpacePage parentPage;
}
