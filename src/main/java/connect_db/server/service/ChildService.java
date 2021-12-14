package connect_db.server.service;

import connect_db.server.models.Child;
import connect_db.server.models.Parent;
import connect_db.server.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    private final ChildRepository childRepository;
    @Autowired
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Child> findAll(){
        return childRepository.findAll();
    }
    public String findByID (Long id){
        return childRepository.findById(id).toString();
    }

    public String existChild (String email, String pass, String name){
        String sql = "SELECT id FROM CHILD WHERE IDPARENT=(select id from Parent where email=? and password=?) and name=?";
        return jdbcTemplate.queryForObject(sql, String.class, email,pass,name);
    }
    public Child saveChild(Child child){
        return childRepository.save(child);
    }

    public String update (String latitude, String longitude, Long id) {
        String sql = "UPDATE CHILD SET latitude=?, longitude=? WHERE id=?";
        return jdbcTemplate.queryForObject(sql, String.class, latitude, longitude, id);
    }
}
