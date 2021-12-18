package connect_db.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import connect_db.server.models.App;
import connect_db.server.models.Child;
import connect_db.server.repository.AppRepository;
import connect_db.server.repository.ChildRepository;
import connect_db.server.repository.Parentrepository;
import net.minidev.json.JSONObject;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class AppService {


    private final AppRepository appRepository;

    @Autowired
    private Parentrepository parentrepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public List<App> findAll() {
        return appRepository.findAll();
    }

    public App create(App app) {
        return appRepository.save(app);
    }

    public String chekExistApp(String name, Long idChild) {
        String sql = "SELECT COUNT(*) FROM App WHERE name=? and child_id=?";
        if (jdbcTemplate.queryForObject(sql, String.class, name, idChild).hashCode() != "0".hashCode()) {
            String sqlId = "SELECT id FROM App WHERE name=? and child_id=?";
            return jdbcTemplate.queryForObject(sqlId, String.class, name, idChild);
        } else
            return null;
    }

    public int updateApp(Long last_time_used, Long total_time, String name, Long idChild) {
        String sql = "UPDATE App SET last_time_used=?, total_time=? WHERE name=? and child_id=?";
        return jdbcTemplate.update(sql, last_time_used, total_time, name, idChild);
    }

    public String getAllByIdChild (Child child){
        final ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(appRepository.getAppsByChild(child));
           // System.out.println(mapper.writeValueAsString(appRepository.getAppsByChild(child)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
