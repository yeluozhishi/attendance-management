package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.signEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SignEntityRepository extends CrudRepository<signEntity,String>,
        JpaSpecificationExecutor<signEntity> {

    /**
     * 查找今日签到记录
     * @param accountnumber
     * @param strtodate
     * @return
     */
    @Query(value = "select se from signEntity se where se.accountnumber=:accountnumber and se.signinTime>=:strtodate")
    signEntity findToday(@Param(value = "accountnumber") String accountnumber, @Param(value = "strtodate") Date strtodate);

    /**
     * 条件查找签到条目
     * @param accountnumber
     * @param startTime
     * @param endTime
     * @param exception
     * @param pageable
     * @return
     */
    @Query(value = "select se from signEntity se where se.accountnumber=:accountnumber and (:startTime is null or se.signinTime>=:startTime) " +
            "and (:endTime is null or se.signinTime<:endTime) and (:exception is null or se.exception = :exception)")
    Page find(@Param(value = "accountnumber") String accountnumber, @Param(value = "startTime") Date startTime,
              @Param(value = "endTime") Date endTime, @Param(value = "exception") String exception, Pageable pageable);

    /**
     * 条件查找签到条目
     * @param accountnumber
     * @param startTime
     * @param endTime
     * @param exception
     * @param pageable
     * @return
     */
    @Query(value = "select se from signEntity se where (:accountnumber is null or se.accountnumber=:accountnumber) and (:startTime is null or se.signinTime>=:startTime) " +
            "and (:endTime is null or se.signinTime<:endTime) and (:exception is null or se.exception = :exception)")
    Page findbyMaster(@Param(value = "accountnumber") String accountnumber, @Param(value = "startTime") Date startTime,
              @Param(value = "endTime") Date endTime, @Param(value = "exception") String exception, Pageable pageable);
    /**
     * 查找签到条目
     * @param accountnumber
     * @param strtodate
     * @param today
     * @return
     */
    @Query(value = "select se from signEntity se where se.accountnumber=:accountnumber and se.signinTime>=:strtodate " +
            "and se.signoutTime<:today ")
    signEntity findByTimeAndAccount(@Param("accountnumber") String accountnumber,@Param("strtodate") Date strtodate,@Param("today") Date today);

    /**
     *
     * @param lastMonth1
     * @param lastMonth2
     * @return
     */
    @Query(value = "select se from signEntity se where se.signinTime>=:lastMonth1 and se.signoutTime<:lastMonth2 ")
    List<signEntity> findByTime(@Param("lastMonth1")Date lastMonth1,@Param("lastMonth2") Date lastMonth2);
}
