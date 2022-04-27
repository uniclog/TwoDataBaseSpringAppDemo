package local.uniclog.app.sqlite.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SQLiteDatabase")
public class SQLiteDBEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Integer number;
    private String text;
}
