package com.gamejam.test.repository;

import java.util.List;

import com.gamejam.test.model.Campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    public List<Campaign> findByName(String name);
}