package local.uniclog.app.h2.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "H2Database")
public class H2DBEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Integer number;
    private String text;
}
