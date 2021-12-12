package connect_db.server.service;

import connect_db.server.models.Child;
import connect_db.server.models.Parent;
import connect_db.server.repository.ChildRepository;
import connect_db.server.repository.Parentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParentService {

    private final Parentrepository parentrepository;
    @Autowired
    public ParentService(Parentrepository parentrepository) {
        this.parentrepository = parentrepository;
    }

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;
    public String findById (Long id){
        return parentrepository.findById(id).toString();
    }

    public boolean existById(Long id) {
        return parentrepository.existsById(id);
    }
    public Long findOne(String nick, String email, String pass){
        Parent parent = new Parent();
        List<Parent> list = parentrepository.findAll();
        boolean is = false;
        int i = 0;
        Long id = Long.valueOf(-1);
        while (is!=true){
            parent = list.get(i);
            if (parent.getEmail().hashCode()==email.hashCode()
                    && parent.getNickname().hashCode() == nick.hashCode()
                    && parent.getPassword().hashCode() == pass.hashCode()){
                id = parent.getId();
                is = true;
            }
        }
        return id;
    }

    public String getAll(String nick, String email){
        final String sql = "SELECT id FROM Parent WHERE nickname=? and email=?";
        return jdbcTemplate.queryForObject(sql, String.class,nick,email);
    }

    public String existNickParent(String nick) {
        final String sql = "SELECT COUNT(*) FROM PARENT WHERE NICKNAME=?";
        return jdbcTemplate.queryForObject(sql,String.class,nick);
    }

    public String checkParent(String email, String password){
        final String sql = "SELECT COUNT(*) FROM PARENT WHERE EMAIL=? and PASSWORD=?";
        return jdbcTemplate.queryForObject(sql, String.class, email,password);
    }

    public String existEmail(String email) {
        final String sql = "SELECT COUNT(*) FROM PARENT WHERE EMAIL=?";
        return jdbcTemplate.queryForObject(sql, String.class, email);
    }

    public List<Parent> findAll(){
        return parentrepository.findAll();
    }

    public Parent saveParent(Parent parent){
        return parentrepository.save(parent);
    }
    public void deleteById(Long id){
        parentrepository.deleteById(id);
    }

    public Parent getParentById(Long idParent){
        return parentrepository.getParentObjectById(idParent);
    }
    public Child create(String name, String latitude, String longtitude, Parent parent){
        Child child = new Child();
        child.setName(name);
        child.setLatitude(latitude);
        child.setLongitude(longtitude);
        child.setParent(parent);
        return childRepository.save(child);
    }

}
