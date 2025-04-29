package th.ac.mahidol.ict.gemini_backend.service;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.Quadrant;
import edu.gemini.app.ocs.model.SciencePlan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;
import edu.gemini.app.ocs.model.StarSystem;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class GeminiService {
    private final OCS ocs = new OCS(true); // initialize once

    public ArrayList<SciencePlan> getAllSciencePlans() {
        return ocs.getAllSciencePlans();
    }

    public String createSciencePlan(SciencePlan sp) {
        return ocs.createSciencePlan(sp);
    }

    public String testSciencePlan(SciencePlan sp) {
        return ocs.testSciencePlan(sp);
    }

    public String submitSciencePlan(SciencePlan sp) {
        return ocs.submitSciencePlan(sp);
    }

    public String testSciencePlanbyID(int ID){
        return ocs.testSciencePlan(ocs.getSciencePlanByNo(ID));
    }

    public String submitSciencePlanbyID(int ID){
        return ocs.submitSciencePlan(ocs.getSciencePlanByNo(ID));
    }

    public List<StarSystem.CONSTELLATIONS> getAllStarSystems() {
        return List.of(StarSystem.CONSTELLATIONS.values());
    }


}


