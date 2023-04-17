package residentialarea.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.StatementBean;

@Repository
public interface StatementDao extends JpaRepository<StatementBean, Integer> {
}
