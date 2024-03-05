package br.com.mutztech.agenda.security.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.mutztech.agenda.security.domain.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    UserDetails findByEmail(String Email);

    //No seu projeto estava sem anotação @Repository
}
