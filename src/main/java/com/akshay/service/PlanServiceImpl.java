package com.akshay.service;

import com.akshay.entity.Plan;
import com.akshay.entity.PlanCategory;
import com.akshay.exception.PlanIdNotFoundException;
import com.akshay.exception.PlanNotFoundException;
import com.akshay.repo.PlanCategoryRepo;
import com.akshay.repo.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements IPlanService {

    @Autowired
    private PlanRepo planRepo;

    @Autowired
    private PlanCategoryRepo planCategoryRepo;


    @Override
    public Map<Integer, String> getPlanCategories() {
        List<PlanCategory> planCategoryList = planCategoryRepo.findAll();
        Map<Integer, String> categoryMap = planCategoryList.stream()
                .collect(Collectors.toMap(
                        PlanCategory::getCategoryId,
                        PlanCategory::getCategoryName));
        return categoryMap;
    }

    @Override
    public Boolean savePlan(Plan plan) {
        Plan savedPlan = planRepo.save(plan);

        /*if (savedPlan.getPlanId()!=null){
            return true;
        }else {
            return false;
        }*/

        //return savedPlan.getPlanId()!=null;

        return savedPlan.getPlanId() != null ? true : false;

    }

    @Override
    public List<Plan> getAllPlans() {
        List<Plan> allPlans = planRepo.findAll();
        return allPlans;
    }

    @Override
    public Plan getPlanByPlanId(Integer planId) {
        Optional<Plan> planById = planRepo.findById(planId);
        /*if (planById.isPresent()){
            return planById.get();
        }else {
            return null;
        }*/
        //return planById.orElse(null);
        // return planById.isPresent()?planById.get():null;
        return planById.orElseThrow(
                () -> new PlanIdNotFoundException("Provided Plan ID [" + planId + "] Not Found")
        );
    }

    @Override
    public Boolean updatePlan(Plan plan) {
        Plan savedPlan = planRepo.save(plan);
        return savedPlan.getPlanId() != null ? true : false;
    }

    @Override
    public Boolean deletePlanByPlanId(Integer planId) {
        Boolean status = false;
        try {
            planRepo.deleteById(planId);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Boolean changePlanStatus(Integer planId, String activeStatus) {
        return planRepo.findById(planId)
                .map(
                        plan -> {
                            plan.setPlanActiveSwitch(activeStatus);
                            planRepo.save(plan);
                            return true;
                        }
                ).orElse(false);
    }
}
