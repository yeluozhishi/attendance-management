package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.managerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ManagerEntityRepository extends CrudRepository<managerEntity,String>,
        JpaSpecificationExecutor<managerEntity> {

    /**
     * 登录
     * @param accountnumber
     * @param password
     * @return
     */
    @Query("select me from managerEntity me where me.accountnumber=:accountnumber and me.password=:password")
    managerEntity findByIdAndPSW(@Param("accountnumber") String accountnumber,@Param("password") String password);
}
