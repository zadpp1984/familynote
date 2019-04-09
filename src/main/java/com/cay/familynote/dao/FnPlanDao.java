package com.cay.familynote.dao;

import com.cay.familynote.bean.FnPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FnPlanDao extends JpaRepository<FnPlan, Long> {
}
