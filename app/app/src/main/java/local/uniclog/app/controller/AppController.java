package local.uniclog.app.controller;

import local.uniclog.app.h2.entity.H2DBEntity;
import local.uniclog.app.h2.service.H2DBService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/AccessController")
public class AppController {
    private final H2DBService serviceH2;

    @PutMapping("/addH2")
    public ResponseEntity<H2DBEntity> addH2() {
        var entity = serviceH2.build();
        entity = serviceH2.save(entity);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<H2DBEntity>> findAll() {
        var entityList = serviceH2.findAll();
        return (entityList != null)
                ? ResponseEntity.ok().body(entityList)
                : ResponseEntity.notFound().build();
    }
}
