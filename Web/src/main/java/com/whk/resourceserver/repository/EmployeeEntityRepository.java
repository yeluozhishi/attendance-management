package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.employeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeEntityRepository extends CrudRepository<employeeEntity,String>,
        JpaSpecificationExecutor<employeeEntity> {

    /**
     * employeeId获取员工信息
     * @param employeeId
     * @return
     */
    @Query("select ee from employeeEntity ee where employeeId=:employeeId")
    employeeEntity findByEmployeeId(@Param(value = "employeeId") Integer employeeId);

    /**
     *
     * @param employeeId
     * @param condition
     * @param pageable
     * @return
     */
    @Query("select ee from employeeEntity ee where  (:employeeId is null or ee.employeeId =:employeeId) " +
            "and ((:condition is null or ee.name like CONCAT('%',:condition,'%')) " +
            "or (:condition is null or ee.position like CONCAT('%',:condition,'%'))) order by ee.entryTime desc")
    Page getEmployee(@Param("employeeId") Integer employeeId, @Param("condition") String condition, Pageable pageable);


}
