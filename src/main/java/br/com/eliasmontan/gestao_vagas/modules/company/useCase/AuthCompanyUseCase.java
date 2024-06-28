package br.com.eliasmontan.gestao_vagas.modules.company.useCase;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.eliasmontan.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.eliasmontan.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Value("${security.token.service}")
    private String secretKey;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{

        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Username/password incorrect");
            });

           ///Verificar a senha são iguais
           var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        
           if(!passwordMatches){
            throw new AuthenticationException();
           }

           Algorithm algorithm = Algorithm.HMAC256(secretKey);
           var token = JWT.create().withIssuer("javagar")
           .withSubject(company.getId().toString())
           .sign(algorithm);

           return token;
    }
}
