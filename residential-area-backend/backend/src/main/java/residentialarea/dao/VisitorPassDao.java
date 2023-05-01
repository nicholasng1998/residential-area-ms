package residentialarea.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.EmergencyRequestBean;
import residentialarea.bean.VisitorPassBean;

import java.util.List;

@Repository
public interface VisitorPassDao extends JpaRepository<VisitorPassBean, String> {
    Page<VisitorPassBean> findAll(Pageable pageable);

    List<VisitorPassBean> findAllByResidentId(int residentId);
}
