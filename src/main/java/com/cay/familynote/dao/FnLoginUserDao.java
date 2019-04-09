package com.cay.familynote.dao;

import com.cay.familynote.bean.FnLoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FnLoginUserDao extends JpaRepository<FnLoginUser, Long> {
    @Query("select loginuser from FnLoginUser loginuser,FnGroupUser groupuser " +
            "where groupuser.groupid = :groupid and loginuser.userid = groupuser.userid")
    List<FnLoginUser> findGroupUsers(@Param("groupid") Long groupid);
}
