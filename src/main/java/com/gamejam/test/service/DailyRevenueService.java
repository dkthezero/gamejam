package com.gamejam.test.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gamejam.test.model.DailyRevenue;
import com.gamejam.test.repository.DailyRevenueRepository;
import com.gamejam.test.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DailyRevenueService {
    private DailyRevenueRepository dailyRevenueRepository;

    @Autowired
    public DailyRevenueService(DailyRevenueRepository dailyRevenueRepository) {
        this.dailyRevenueRepository = dailyRevenueRepository;
    }

    public DailyRevenue create(DailyRevenue c) {
        return dailyRevenueRepository.save(c);
    }

    public List<DailyRevenue> getByCampaignIds(List<Integer> campIds) {
        return dailyRevenueRepository.findAllByCampaignIdIn(campIds);
    }

    public double getTotalToDate(List<Integer> campIds, Date date) {
        Date begin = DateUtil.getDate("2020-03-13");

        List<DailyRevenue> l = dailyRevenueRepository.findAllByCampaignIdInAndDateBetween(campIds, begin, date);
        return l.stream().collect(Collectors.summarizingDouble(DailyRevenue::getActualyRevenue)).getSum();
    }
}
