package residentialarea.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.ClientBean;

@Repository
public interface ClientDao extends JpaRepository<ClientBean, Integer> {
}
