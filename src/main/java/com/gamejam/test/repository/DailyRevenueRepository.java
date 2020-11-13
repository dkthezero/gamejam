package com.gamejam.test.repository;

import java.util.Date;
import java.util.List;

import com.gamejam.test.model.DailyRevenue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRevenueRepository extends JpaRepository<DailyRevenue, Integer> {
    public List<DailyRevenue> findAllByCampaignIdIn(List<Integer> campaignIds);
    public List<DailyRevenue> findAllByCampaignIdInAndDateBetween(List<Integer> campaignIds, Date from, Date to);
}
