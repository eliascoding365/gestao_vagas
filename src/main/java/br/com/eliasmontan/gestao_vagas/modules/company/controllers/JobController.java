package br.com.eliasmontan.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eliasmontan.gestao_vagas.modules.company.dto.CreateJobDto;
import br.com.eliasmontan.gestao_vagas.modules.company.entities.JobEntity;
import br.com.eliasmontan.gestao_vagas.modules.company.useCase.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;
    
    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
        .benefits(createJobDto.getBenefits())
        .companyId(UUID.fromString(companyId.toString()))
        .description(createJobDto.getDescription())
        .level(createJobDto.getLevel())
        ;
        return createJobUseCase.execute(jobEntity);
    }
}
