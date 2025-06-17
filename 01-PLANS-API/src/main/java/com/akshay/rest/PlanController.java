package com.akshay.rest;

import com.akshay.constants.AppConstants;
import com.akshay.entity.Plan;
import com.akshay.properties.AppProperties;
import com.akshay.service.IPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plans/api")
public class PlanController {

    private Map<String, String> messages;

    private IPlanService service;

    public PlanController(IPlanService service, AppProperties appProperties) {
        this.service = service;
        this.messages = appProperties.getMessages();
        System.out.println(this.messages);
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> getPlanCategories() {
        Map<Integer, String> planCategories = service.getPlanCategories();
        return new ResponseEntity<>(planCategories, HttpStatus.OK);
    }

    /*@PostMapping("/plan")
    public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
        Boolean savedPlan = service.savePlan(plan);
        String responseMsg="";
        if (savedPlan) {
            return new ResponseEntity<>(responseMsg="Plan Saved with Plan ID: " + plan.getPlanId(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(responseMsg="Plan could not be saved", HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @PostMapping("/save")
    public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
        boolean isSaved = service.savePlan(plan);

        if (isSaved) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(messages.get(AppConstants.PLAN_SAVE_SUCC) + plan.getPlanId());
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(AppConstants.PLAN_SAVE_FAIL);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> getAllPlans() {
        List<Plan> allPlans = service.getAllPlans();
        return new ResponseEntity<>(allPlans, HttpStatus.OK);

    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
        Plan planByPlanId = service.getPlanByPlanId(planId);
        return new ResponseEntity<>(planByPlanId, HttpStatus.OK);

    }

    @PutMapping("/plan")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
        Boolean updatedPlan = service.updatePlan(plan);
        if (updatedPlan) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(messages.get(AppConstants.PLAN_UPDATE_SUCC));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(messages.get(AppConstants.PLAN_UPDATE_FAIL));
        }
    }

    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
        Boolean deletedPlan = service.deletePlanByPlanId(planId);
        if (deletedPlan) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(messages.get(AppConstants.PLAN_DELETE_SUCC) + planId);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(messages.get(AppConstants.PLAN_DELETE_FAIL));
        }
    }

    @PatchMapping("/active-status/{planId}/{activeStatus}")
    public ResponseEntity<String> changeActiveStatus(Integer planId, String activeStatus) {
        Boolean changedPlanStatus = service.changePlanStatus(planId, activeStatus);
        if (changedPlanStatus) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCC));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL));
    }
}
