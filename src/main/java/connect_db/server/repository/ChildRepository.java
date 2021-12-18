package connect_db.server.repository;

import connect_db.server.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChildRepository extends JpaRepository<Child, Long> {

    @Query(value = "SELECT c FROM Child c WHERE c.id=?1")
    Child getChildByID(Long id);
}
