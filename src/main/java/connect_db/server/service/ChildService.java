package connect_db.server.service;

import connect_db.server.models.Child;
import connect_db.server.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Child saveChild(Child child){
        return childRepository.save(child);
    }

    public String update (String latitude, String longitude, Long id) {
        String sql = "UPDATE CHILD SET latitude=?, longitude=? WHERE id=?";
        Date date = new Date();
        System.out.println(date);
        return String.valueOf(jdbcTemplate.update(sql, latitude, longitude, id));
    }

    public Long getChildId (Long idParent, String name) {
        String sql = "SELECT id FROM Child WHERE parent_id=? and name=?";
        String res = jdbcTemplate.queryForObject(sql, String.class, idParent, name );
        return Long.valueOf(res);
    }
    public Child getChildById (Long idChild){
        return childRepository.getChildByID(idChild);
    }

    public String getGeoChild (Long id) {
        StringBuilder sb = new StringBuilder();
        String sql1 = "SELECT latitude FROM Child WHERE id=?";
        sb.append(jdbcTemplate.queryForObject(sql1, String.class, id));
        sb.append("\n");
        String sql2 = "SELECT longitude FROM Child WHERE id=?";
        sb.append(jdbcTemplate.queryForObject(sql2, String.class, id));
        return sb.toString();
    }

    public String location (String location, Long idChild){
        String sql = "UPDATE CHILD SET location=? WHERE id=?";
        return String.valueOf(jdbcTemplate.update(sql,location,idChild));
    }

    public String getLocation(Long idChild) {
        String sql = "SELECT location FROM Child WHERE id=?";
        return jdbcTemplate.queryForObject(sql,String.class, idChild);
    }

}
