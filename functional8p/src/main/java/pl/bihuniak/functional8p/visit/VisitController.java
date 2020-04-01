package pl.bihuniak.functional8p.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitController {
    private final DiseaseRepository diseaseRepository;
    private final MedicalServiceRepository medicalServiceRepository;
    private final VisitRepository visitRepository;

    @PostMapping("/services")
    public MedicalService addMedicalService(MedicalService medicalService){
        return medicalServiceRepository.save(medicalService);
    }

    @PostMapping("/diseases")
    public Disease addDisease(Disease disease){
        return diseaseRepository.save(disease);
    }

    @PostMapping("/visits")
    public Visit addVisit(VisitRequest visitRequest){
        List<Disease> diseases = diseaseRepository.findByIdIn(visitRequest.getDiseases());
        if(diseases.size() != visitRequest.getDiseases().size()){
            throw new RuntimeException();
        }
        List<MedicalService> medicalServices = medicalServiceRepository.findByIdIn(visitRequest.getServices());
        if(medicalServices.size() != visitRequest.getServices().size()){
            throw new RuntimeException();
        }
        Visit visit = new Visit(null, visitRequest.getDate(), diseases, medicalServices);
        return visitRepository.save(visit);
    }
}
