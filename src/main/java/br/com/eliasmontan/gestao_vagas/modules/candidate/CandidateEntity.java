package br.com.eliasmontan.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class CandidateEntity {

  private UUID id;
  private String name;
  @NotBlank
  @Pattern(regexp = "\\S+", message = "O campo [usernam] não deve conter espaco")
  private String username;

  @Email (message = "o campo [email] deve conter email valido")
  private String email;
  
  @Length (min= 10, max= 20)
  private String password;

  private String description;
  private String curriculum;
}
