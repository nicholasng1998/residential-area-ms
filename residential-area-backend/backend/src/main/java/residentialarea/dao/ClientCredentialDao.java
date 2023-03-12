package residentialarea.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.ClientCredentialBean;

@Repository
public interface ClientCredentialDao extends JpaRepository<ClientCredentialBean, Integer> {

    ClientCredentialBean findByUsername(String username);
}
