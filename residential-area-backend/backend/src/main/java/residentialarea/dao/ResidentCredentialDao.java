package residentialarea.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.ResidentCredentialBean;

@Repository
public interface ResidentCredentialDao extends JpaRepository<ResidentCredentialBean, Integer> {

    ResidentCredentialBean findByUsername(String username);

    ResidentCredentialBean findByResidentId(Integer id);

}
