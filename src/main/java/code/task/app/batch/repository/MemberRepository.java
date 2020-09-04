package code.task.app.batch.repository;

import code.task.app.batch.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>{

    @Query(
            nativeQuery = true ,
            value = " SELECT A.REGISTER" +
                    "      , RANK() OVER (ORDER BY SUM(A.POST_POINT) + SUM(A.REPLY_POINT) DESC) AS RANK " +
                    " FROM ( " +
                    "       SELECT REGISTER " +
                    "            , COUNT(1) * 10 AS POST_POINT " +
                    "            , '' AS REPLY_POINT " +
                    "       FROM POST " +
                    "       WHERE CREATED_DATE BETWEEN DATE_ADD(NOW(),INTERVAL -1 MONTH ) AND NOW() " +
                    "       GROUP BY REGISTER " +
                    "       UNION ALL " +
                    "       SELECT REGISTER " +
                    "            , '' AS POST_POINT " +
                    "            , COUNT(1) * 5 AS REPLY_POINT " +
                    "       FROM REPLY " +
                    "       WHERE CREATED_DATE BETWEEN DATE_ADD(NOW(),INTERVAL -1 MONTH ) AND NOW() " +
                    "       GROUP BY REGISTER " +
                    "      ) A " +
                    " GROUP BY A.REGISTER "
    )
    List<Object[]> selectRankList();

    Member findById(String register);
}