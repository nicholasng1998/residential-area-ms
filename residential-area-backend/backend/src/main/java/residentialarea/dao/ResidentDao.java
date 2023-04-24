package residentialarea.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.ResidentBean;

@Repository
public interface ResidentDao extends JpaRepository<ResidentBean, Integer> {
    Page<ResidentBean> findAll(Pageable pageable);
}
