package local.uniclog.app.sqlite.repository;

import local.uniclog.app.sqlite.entity.SQLiteDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLiteDBRepository extends JpaRepository<SQLiteDBEntity, Long> {
}
