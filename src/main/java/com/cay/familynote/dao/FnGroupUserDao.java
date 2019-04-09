package com.cay.familynote.dao;

import com.cay.familynote.bean.FnGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FnGroupUserDao extends JpaRepository<FnGroupUser, Long> {

}
