package com.cay.familynote.dao;

import com.cay.familynote.bean.FnGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FnGroupDao extends JpaRepository<FnGroup, Long> {
    @Query("select group from FnGroup group,FnGroupUser b where b.userid = :userid and group.groupid=b.groupid")
    public List<FnGroup> findGroupByUserid(@Param("userid") long userid);
}
