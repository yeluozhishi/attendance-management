package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.accountEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntityRepository extends CrudRepository<accountEntity,String>,
        JpaSpecificationExecutor<accountEntity> {

    //登录
    @Query(value = "select * from account t where t.accountnumber=?1 and t.password=?2",nativeQuery = true)
    accountEntity findUser(String accountnumber,String password);

    //查找账户
    @Query(value = "select * from account t where t.employee_id=?1",nativeQuery = true)
    accountEntity findOneByEmployeeId(Integer employeeId);

    //查找账户名是否存在
    @Query(value = "select t from accountEntity t where t.accountnumber =:accountnumber")
    accountEntity findOneBYAccount(@Param("accountnumber") String accountnumber);
}
