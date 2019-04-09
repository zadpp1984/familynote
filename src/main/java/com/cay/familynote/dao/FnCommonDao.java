package com.cay.familynote.dao;

import com.cay.familynote.bean.FnGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FnCommonDao extends JpaRepository<FnGroup, Long> {



}
