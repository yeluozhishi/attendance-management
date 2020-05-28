package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.signDiaryEntity;
import com.whk.resourceserver.entity.signEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SignDiaryEntityRepository extends CrudRepository<signDiaryEntity,String>,
        JpaSpecificationExecutor<signDiaryEntity> {

    /**
     * 查找今日签到次数
     * @param accountnumber
     * @param strtodate
     * @return
     */
    @Query(value = "select count(sde) from signDiaryEntity sde where sde.accountnumber=:accountnumber and sde.signTime>=:strtodate")
    int findToday(@Param("accountnumber") String accountnumber,@Param("strtodate") Date strtodate);

    /**
     * 获取昨日签到条目
     * @param date
     * @param strtodate
     * @return
     */
    @Query(value = "select * from signdiary where sign_time>=?1 and sign_time<?2 ",nativeQuery = true)
    List<signDiaryEntity> findByTime( @Param("date") Date date, @Param("strtodate") Date strtodate);

    /**
     * 查找今日签到
     * @param accountnumber
     * @param strtodate
     * @return
     */
    @Query(value = "select sde from signDiaryEntity sde where sde.accountnumber=:accountnumber and sde.signTime>=:strtodate order by sde.signTime desc ")
    List<signDiaryEntity> findState(@Param("accountnumber") String accountnumber,@Param("strtodate") Date strtodate);

}
