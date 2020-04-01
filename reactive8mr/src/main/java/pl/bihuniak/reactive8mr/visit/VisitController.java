package pl.bihuniak.reactive8mr.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class VisitController {
    private final DiseaseRepository diseaseRepository;
    private final MedicalServiceRepository medicalServiceRepository;
    private final VisitRepository visitRepository;

    @PostMapping("/services")
    public Mono<MedicalService> addMedicalService(Mono<MedicalService> medicalService){
        return medicalService
            .flatMap(medicalServiceRepository::save);
    }

    @PostMapping("/diseases")
    public Mono<Disease> addDisease(Mono<Disease> disease){
        return disease
            .flatMap(diseaseRepository::save);
    }

    @PostMapping("/visits")
    public Mono<Visit> addVisit(Mono<VisitRequest> visitRequest){
        return visitRequest
            .flatMap(v -> Mono.just(v)
                .filterWhen(z -> diseaseRepository.findByIdIn(v.getDiseases())
                    .count()
                    .filter(x -> x != v.getDiseases().size())
                    .hasElement()
                )
                .switchIfEmpty(Mono.error(new RuntimeException()))
            )
            .flatMap(v -> Mono.just(v)
                .filterWhen(z -> medicalServiceRepository.findByIdIn(v.getServices())
                    .count()
                    .filter(x -> x != v.getDiseases().size())
                    .hasElement()
                )
                .switchIfEmpty(Mono.error(new RuntimeException()))
            )
            .map(x -> new Visit(null, x.getDate(), x.getDiseases(), x.getServices()))
            .flatMap(visitRepository::save);
    }

    @GetMapping("/visits")
    public Flux<Visit> addVisit(@RequestParam LocalDate date, @RequestParam String medicalService){
        return visitRepository.findAll()
            .filter(x -> x.getDate().isBefore(date))
            .map(x -> Tuples.of(x, medicalServiceRepository.findByIdIn(x.getMedicalServices())))
            .filterWhen(x -> x.getT2().all(y -> y.getName().startsWith(medicalService)))
            .map(Tuple2::getT1);
    }
}
