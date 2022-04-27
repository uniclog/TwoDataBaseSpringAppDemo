package local.uniclog.app.h2.repository;

import local.uniclog.app.h2.entity.H2DBEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2DBRepository extends JpaRepository<H2DBEntity, Long> {
}
