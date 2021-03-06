package com.tensquare.qa.dao;


import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    /**
     * 根据标签ID查询最新问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in( select problemid from Pl where labelid=?1 ) order by replytime desc")
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);


    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ?", nativeQuery = true)
    List<Problem> newlist(String labelid, Pageable pageable);


    /**
     * 根据标签ID查询热门问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in( select problemid from Pl where labelid=?1 ) order by reply desc")
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);


    /**
     * 根据标签ID查询等待回答列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in( select problemid from Pl where labelid=?1 ) and reply=0 order by createtime desc")
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);


}
