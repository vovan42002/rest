package connect_db.server.repository;

import connect_db.server.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Parentrepository extends JpaRepository<Parent, Long> {

    @Query(value = "SELECT p FROM Parent p WHERE p.id=?1")
    Parent getParentObjectById(Long id);

}
