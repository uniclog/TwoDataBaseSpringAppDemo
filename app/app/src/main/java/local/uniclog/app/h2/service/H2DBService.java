package local.uniclog.app.h2.service;

import local.uniclog.app.h2.entity.H2DBEntity;
import local.uniclog.app.h2.repository.H2DBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class H2DBService {
    private final H2DBRepository repository;

    public H2DBEntity build() {
        Random random = new Random();
        return H2DBEntity.builder()
                .text("Text" + random.nextInt(50) + random.nextInt(50) + random.nextInt(50))
                .number(random.nextInt(50))
                .build();
    }

    public List<H2DBEntity> findAll() {
        return repository.findAll();
    }

    public H2DBEntity save(H2DBEntity entity) {
        return repository.save(entity);
    }
}
