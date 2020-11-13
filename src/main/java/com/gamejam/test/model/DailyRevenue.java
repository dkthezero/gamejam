package com.gamejam.test.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Phạm Nhật Hưng
 */
@Entity
@Table(name = "daily_revenue")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyRevenue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "campaign_id", nullable = false)
    private int campaignId;

    @Column(name = "date", nullable = false)
    private Date date;
    
    @Column(name = "actualy_revenue", nullable = false)
    private double actualyRevenue;

    @Column(name = "predict_revenue", nullable = false)
    private double predictRevenue;
}