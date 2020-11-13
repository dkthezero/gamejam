package com.gamejam.test.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gamejam.test.model.Campaign;
import com.gamejam.test.service.CampaignService;
import com.gamejam.test.service.DailyRevenueService;
import com.gamejam.test.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CampaignController {
    private CampaignService campaignService;
    private DailyRevenueService dailyRevenueService;


    @Autowired
    public CampaignController(CampaignService campaignService,DailyRevenueService dailyRevenueService) {
        this.campaignService = campaignService;
        this.dailyRevenueService = dailyRevenueService;
    }

    @GetMapping("/{campaignName}/predict_date")
    @ResponseBody
    public ResponseEntity<Integer> getTotalDay(@PathVariable String campaignName) {
        return new ResponseEntity<>(campaignService.prediction(campaignName), HttpStatus.OK);
    }

    @GetMapping("/{campaignName}/revenue")
    @ResponseBody
    public ResponseEntity<Double> getRevenue(@PathVariable String campaignName, @RequestParam(name = "project_day") String day) {
        Date date = DateUtil.getDate(day);
        List<Campaign> campaigns = campaignService.getByName(campaignName);
        List<Integer> cmpIds = campaigns.stream().map(Campaign::getId).collect(Collectors.toList());
        double total = dailyRevenueService.getTotalToDate(cmpIds, date);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
