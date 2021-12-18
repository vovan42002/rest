package connect_db.server.repository;

import connect_db.server.models.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, Long> {
}
