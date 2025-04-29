package th.ac.mahidol.ict.gemini_backend.controller;
import edu.gemini.app.ocs.model.SciencePlan;
import org.springframework.web.bind.annotation.*;
import th.ac.mahidol.ict.gemini_backend.service.GeminiService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/plans")
    public ArrayList<SciencePlan> getAllPlans() {
        return geminiService.getAllSciencePlans();
    }

    @PostMapping("/plan/create")
    public String createPlan(@RequestBody SciencePlan plan) {
        return geminiService.createSciencePlan(plan);
    }

    @PostMapping("/plan/test")
    public String testPlan(@RequestBody SciencePlan plan) {
        return geminiService.testSciencePlan(plan);
    }

    @PostMapping("/plan/submit")
    public String submitPlan(@RequestBody SciencePlan plan) {
        return geminiService.submitSciencePlan(plan);
    }

    @PostMapping("/plan/test/{ID}")
    public String testPlanbyID(@PathVariable  int ID){
        return geminiService.testSciencePlanbyID(ID);
    }

    @PostMapping("/plan/submit/{ID}")
    public String submitPlanbyID(@PathVariable int ID) {
        return geminiService.submitSciencePlanbyID(ID);
    }
}
