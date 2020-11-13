package com.gamejam.test.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gamejam.test.model.Campaign;
import com.gamejam.test.model.DailyRevenue;
import com.gamejam.test.repository.CampaignRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CampaignService {
    private CampaignRepository campaignRepository;
    private DailyRevenueService dailyRevenueService;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository,DailyRevenueService dailyRevenueService) {
        this.campaignRepository = campaignRepository;
        this.dailyRevenueService = dailyRevenueService;
    }

    public Campaign create(Campaign c) {
        return campaignRepository.save(c);
    }

    public List<Campaign> getByName(String campaignName) {
        return campaignRepository.findByName(campaignName);
    }

    /**
     * Predict
     * @param campaignName
     */
    public int prediction(String campaignName){
        List<Campaign> campaigns = getByName(campaignName);
        int totalDay = 0;
        if (!campaigns.isEmpty()) {
            double totalSpend = campaigns.stream().map(Campaign::getSpends).reduce(0.0, (a, b)->a+b);
            Date firstDate = campaigns.stream().map(Campaign::getDate).min(Date::compareTo).get();
            Date lastDate = campaigns.stream().map(Campaign::getDate).max(Date::compareTo).get();

            List<Integer> campIds = campaigns.stream().map(Campaign::getId).collect(Collectors.toList());
            List<DailyRevenue> dailyRevenues = dailyRevenueService.getByCampaignIds(campIds);
            final int totalLauchDay = (int) dailyRevenues.stream().map(DailyRevenue::getDate).distinct().count();
            Map<Integer, List<DailyRevenue>> groups = dailyRevenues.stream().filter(r->r.getActualyRevenue()!=0.0).collect(Collectors.groupingBy(DailyRevenue::getCampaignId));

        }
        return totalDay;
    }
}
