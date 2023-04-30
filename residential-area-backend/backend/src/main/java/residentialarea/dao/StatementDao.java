package residentialarea.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import residentialarea.bean.StatementBean;
import residentialarea.bean.VisitorPassBean;

import java.util.List;

@Repository
public interface StatementDao extends JpaRepository<StatementBean, Integer> {

    @Query(value = "SELECT * FROM statement WHERE status=:status and year=:year and month=:month", nativeQuery = true)
    List<StatementBean> findAllByStatusAndYearAndMonth(@Param("status") String status,
                                                       @Param("year") Integer year,
                                                       @Param("month") Integer month);
}
