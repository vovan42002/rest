package connect_db.server.repository;

import connect_db.server.models.App;
import connect_db.server.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AppRepository extends JpaRepository<App, Long> {

    @Query(value = "SELECT a FROM App a WHERE a.child=?1")
    List<App> getAppsByChild (Child child);
}
