package residentialarea.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.EmergencyRequestBean;

@Repository
public interface EmergencyRequestDao extends JpaRepository<EmergencyRequestBean, Integer> {
    Page<EmergencyRequestBean> findAll(Pageable pageable);
}
