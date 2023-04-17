package residentialarea.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.ReceiptBean;

@Repository
public interface ReceiptDao extends JpaRepository<ReceiptBean, Integer> {
}
