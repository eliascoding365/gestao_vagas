package br.com.eliasmontan.gestao_vagas.modules.candidate.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eliasmontan.gestao_vagas.exceptions.UserFoundException;
import br.com.eliasmontan.gestao_vagas.modules.candidate.company.entities.CompanyEntity;
import br.com.eliasmontan.gestao_vagas.modules.candidate.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {

    this.companyRepository
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        }

        );
    return this.companyRepository.save(companyEntity);

  }
}
