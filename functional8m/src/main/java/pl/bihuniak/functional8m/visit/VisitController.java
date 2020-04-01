package pl.bihuniak.functional8m.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.jooq.lambda.tuple.Tuple.tuple;

@RestController
@RequiredArgsConstructor
public class VisitController{
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
        Visit visit = new Visit(null, visitRequest.getDate(), visitRequest.getDiseases(), visitRequest.getServices());
        return visitRepository.save(visit);
    }

    @GetMapping("/visits")
    public List<Visit> addVisit(@RequestParam LocalDate date, @RequestParam String medicalService){
        return visitRepository.findAll().stream()
            .filter(x -> x.getDate().isBefore(date))
            .map(x -> tuple(x, medicalServiceRepository.findByIdIn(x.getMedicalServices())))
            .filter(x -> x.v2.stream().allMatch(y -> y.getName().startsWith(medicalService)))
            .map(x -> x.v1)
            .collect(toList());
    }
}
