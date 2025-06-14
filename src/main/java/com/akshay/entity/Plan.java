package com.akshay.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TBL_PLANS")
public class Plan {

    @Id
    @GeneratedValue
    private Integer planId;

    private String planName;

    private LocalDate planStartDate;

    private LocalDate planEndDate;

    private String planActiveSwitch;

    private Integer planCategoryId;

    private String planCreatedBy;

    private String planUpdatedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate planCreatedDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate planUpdatedDate;


}
