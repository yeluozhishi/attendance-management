package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.offworkapplicationEntity;
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
public interface OffWorkApplicationEntityRepository extends CrudRepository<offworkapplicationEntity,String>,
        JpaSpecificationExecutor<offworkapplicationEntity> {

    /**
     * 条件搜索请假申请
     * @param employeeId
     * @param applicationType
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    @Query(value = "select owae from offworkapplicationEntity owae where owae.employeeId=:employeeId " +
            "and (:applicationType is null or owae.applicationType=:applicationType) " +
            "and (:startTime is null or owae.createTime>=:startTime) " +
            "and (:endTime is null or owae.createTime<=:endTime) " +
            "order by owae.createTime desc ")
    Page find(@Param(value = "employeeId") Integer employeeId, @Param(value = "applicationType") String applicationType,
              @Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime, Pageable pageable);

    /**
     * 条件搜索请假申请（管理）
     * @param employeeId
     * @param reviewResult
     * @param applicationType
     * @param condition
     * @param pageable
     * @return
     */
    @Query(value = "select owae from offworkapplicationEntity owae where (:employeeId is null or owae.employeeId=:employeeId) " +
            "and (:applicationType is null or owae.applicationType=:applicationType) " +
            "and (:reviewResult is null or owae.reviewResult=:reviewResult) " +
            "and (:condition is null or owae.applicationReason like CONCAT('%',:condition,'%')) order by owae.createTime desc" )
    Page findByMaster(@Param(value = "employeeId") Integer employeeId,@Param(value = "reviewResult") String reviewResult,
                      @Param(value = "applicationType") String applicationType,
                      @Param(value = "condition") String condition, Pageable pageable);

    @Query(value = "select owae from offworkapplicationEntity owae where (:employeeId is null or owae.employeeId=:employeeId) " +
            "and (:applicationType is null or owae.applicationType=:applicationType) " +
            "and (owae.reviewResult is null or owae.reviewResult='') " +
            "and (:condition is null or owae.applicationReason like CONCAT('%',:condition,'%') ) order by owae.createTime desc" )
    Page findByMaster(@Param(value = "employeeId") Integer employeeId,
                      @Param(value = "applicationType") String applicationType,
                      @Param(value = "condition") String condition,Pageable pageable);

    /**
     * 根据id查找是否存在申请条目
     * @param employeeId
     * @param id
     * @return
     */
    @Query(value = "select owae from offworkapplicationEntity owae where owae.employeeId=:employeeId " +
            "and owae.id=:id ")
    offworkapplicationEntity findByIdAndEmployeeId(@Param(value = "employeeId") Integer employeeId, @Param(value = "id") Integer id);

    /**
     *
     * @param lastMonth1
     * @param lastMonth2
     * @return
     */
    @Query(value = "select owae from offworkapplicationEntity owae where owae.createTime>=:lastMonth1 and owae.createTime<:lastMonth2 ")
    List<offworkapplicationEntity> findByTime(@Param(value = "lastMonth1")Date lastMonth1,@Param(value = "lastMonth2") Date lastMonth2);
}
