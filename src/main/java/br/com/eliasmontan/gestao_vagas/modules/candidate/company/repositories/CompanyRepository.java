package br.com.eliasmontan.gestao_vagas.modules.candidate.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eliasmontan.gestao_vagas.modules.candidate.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity , UUID> {
  Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
}
