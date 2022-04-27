package local.uniclog.app.controller;

import local.uniclog.app.h2.entity.H2DBEntity;
import local.uniclog.app.h2.service.H2DBService;
import local.uniclog.app.sqlite.entity.SQLiteDBEntity;
import local.uniclog.app.sqlite.service.SQLiteDBService;
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
@RequestMapping("/api")
public class AppController {
    private final H2DBService serviceH2;
    private final SQLiteDBService serviceSQLite;

    @PutMapping("/H2/addH2")
    public ResponseEntity<H2DBEntity> addH2() {
        var entity = serviceH2.build();
        entity = serviceH2.save(entity);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/H2/findAll")
    public ResponseEntity<List<H2DBEntity>> findAllH2() {
        var entityList = serviceH2.findAll();
        return (entityList != null)
                ? ResponseEntity.ok().body(entityList)
                : ResponseEntity.notFound().build();
    }


    @PutMapping("/SQLite/add")
    public ResponseEntity<SQLiteDBEntity> addSQLite() {
        var entity = serviceSQLite.build();
        entity = serviceSQLite.save(entity);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/SQLite/findAll")
    public ResponseEntity<List<SQLiteDBEntity>> findAllSQLite() {
        var entityList = serviceSQLite.findAll();
        return (entityList != null)
                ? ResponseEntity.ok().body(entityList)
                : ResponseEntity.notFound().build();
    }
}
