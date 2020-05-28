package com.whk.resourceserver.repository;

import com.whk.resourceserver.entity.memorandumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MemorandumEntityRepository extends CrudRepository<memorandumEntity,String>,
        JpaSpecificationExecutor<memorandumEntity> {

    /**
     * 备忘录模糊查询
     * @param accountnumber
     * @param title
     * @param content
     * @param belongDateStart
     * @param belongDateEnd
     * @param updataTimeStart
     * @param updataTimeEnd
     * @param pageable
     * @return
     */
    @Query(value = "select me from memorandumEntity me where me.accountnumber=:accountnumber " +
            "and (:title is null or me.title like CONCAT('%',:title,'%') ) " +
            "and (:content is null or me.content like CONCAT('%',:content,'%') ) " +
            "and (:belongDateStart is null or me.belongDate>=:belongDateStart) " +
            "and (:belongDateEnd is null or me.belongDate<=:belongDateEnd) " +
            "and (:updataTimeStart is null or me.updataTime>=:updataTimeStart) " +
            "and (:updataTimeEnd is null or me.updataTime<=:updataTimeEnd) " )
    Page findAllItem( @Param("accountnumber") String accountnumber, @Param("title") String title, @Param("content") String content,
                      @Param("belongDateStart") Date belongDateStart, @Param("belongDateEnd") Date belongDateEnd,
                      @Param("updataTimeStart") Date updataTimeStart, @Param("updataTimeEnd") Date updataTimeEnd, Pageable pageable);

    /**
     * 备忘录单条查询
     * @param strtodate
     * @param accountnumber
     * @return
     */
    @Query(value = "select me from memorandumEntity me where me.accountnumber=:accountnumber and me.belongDate=:strtodate")
    memorandumEntity findOneByAccountnumber(@Param("strtodate") Date strtodate, @Param("accountnumber") String accountnumber);
}
