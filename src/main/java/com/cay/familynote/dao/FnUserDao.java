package com.cay.familynote.dao;

import com.cay.familynote.bean.FnUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FnUserDao extends JpaRepository<FnUser,Long> {

}
