package residentialarea.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.PaymentBean;

@Repository
public interface PaymentDao extends JpaRepository<PaymentBean, Integer> {
    Page<PaymentBean> findAll(Pageable pageable);
}
