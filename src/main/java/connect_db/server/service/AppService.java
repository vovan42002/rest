package connect_db.server.service;

import connect_db.server.models.App;
import connect_db.server.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
    private final AppRepository appRepository;

    @Autowired
    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<App> findAll (){
        return appRepository.findAll();
    }

    public App create (App app) {
        return appRepository.save(app);
    }
}
