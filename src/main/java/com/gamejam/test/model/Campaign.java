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
@Table(name = "campaigns")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campaign implements Serializable {
    private static final long serialVersionUID = 8602907790051701377L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "installs", nullable = false)
    private int installs;

    @Column(name = "spends", nullable = false)
    private double spends;
}