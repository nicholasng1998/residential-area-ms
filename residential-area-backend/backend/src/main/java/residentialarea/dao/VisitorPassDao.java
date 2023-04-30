package residentialarea.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.VisitorPassBean;

@Repository
public interface VisitorPassDao extends JpaRepository<VisitorPassBean, Integer> {
    Page<VisitorPassBean> findAll(Pageable pageable);
}
