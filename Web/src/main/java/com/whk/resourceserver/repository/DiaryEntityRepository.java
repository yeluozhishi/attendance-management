package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.diaryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DiaryEntityRepository extends CrudRepository<diaryEntity,String>,
        JpaSpecificationExecutor<diaryEntity> {

//    @Query(value = "select * from account t where t.accountnumber=?1 and t.password=?2",nativeQuery = true)
//    accountEntity findUser(@Param(value = "accountnumber") String accountnumber, @Param(value = "password") String password);
}
