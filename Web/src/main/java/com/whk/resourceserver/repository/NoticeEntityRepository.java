package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.noticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NoticeEntityRepository extends CrudRepository<noticeEntity,String>,
        JpaSpecificationExecutor<noticeEntity> {

    /**
     * 获取任务
     * @param pageable
     * @return
     */
    @Query("select me from noticeEntity me order by me.createTime desc")
    Page findByEmployee(Pageable pageable);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Query("select me from noticeEntity me where me.id=:id order by me.createTime desc")
    noticeEntity findOneBYid(@Param("id") int id);

    @Query("select me from noticeEntity me " +
            "where (:id is null or me.id =:id) " +
            "and ((:condition is null or me.accountnumber like CONCAT('%',:condition,'%')) " +
            "or (:condition is null or me.accountnumber_name like CONCAT('%',:condition,'%')) " +
            "or (:condition is null or me.missionContent like CONCAT('%',:condition,'%'))) "+
            " order by me.createTime desc")
    Page findByCondition(@Param("id") Integer id,@Param("condition") String condition, Pageable pageable);
}
