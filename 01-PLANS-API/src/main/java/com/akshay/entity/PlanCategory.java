package com.akshay.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "TBL_PLAN_CATEGORY")
public class PlanCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private String categoryActiveSwitch;

    private String categoryCreatedBy;

    private String categoryUpdatedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate categoryCreatedDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate categoryUpdatedDate;
}
