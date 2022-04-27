package local.uniclog.app.sqlite.service;

import local.uniclog.app.sqlite.entity.SQLiteDBEntity;
import local.uniclog.app.sqlite.repository.SQLiteDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQLiteDBService {
    private final SQLiteDBRepository repository;

    public SQLiteDBEntity build() {
        var random = new Random();
        return SQLiteDBEntity.builder()
                .text("Text" + random.nextInt(50) + random.nextInt(50) + random.nextInt(50))
                .number(random.nextInt(50))
                .build();
    }

    public List<SQLiteDBEntity> findAll() {
        return repository.findAll();
    }

    public SQLiteDBEntity save(SQLiteDBEntity entity) {
        return repository.save(entity);
    }
}
