package residentialarea.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import residentialarea.bean.NoticeBean;

import java.util.Date;
import java.util.List;

@Repository
public interface NoticeDao extends JpaRepository<NoticeBean, Integer> {

    List<NoticeBean> findByIsActiveAndExpiryDateAfter(String isActive, Date currentDate);
    NoticeBean findById(int id);

    Page<NoticeBean> findAll(Pageable pageable);
}
