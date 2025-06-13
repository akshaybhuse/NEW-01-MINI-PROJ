package com.akshay.service;

import com.akshay.entity.Plan;

import java.util.List;
import java.util.Map;

public interface IPlanService {

    //get plan category with ID and name
    public Map<Integer,String> getPlanCategories();

    //insert plan
    public Boolean savePlan(Plan plan);

    //get all Plans
    public List<Plan> getAllPlans();

    //get planById for update operation
    public Plan getPlanByPlanId(Integer planId);

    //update plan
    public Boolean updatePlan(Plan plan);

    //delete plan based on plan id
    public Boolean deletePlanByPlanId(Integer planId);

    //change plan status 
    public Boolean changePlanStatus(Integer planId, String activeStatus);
}
