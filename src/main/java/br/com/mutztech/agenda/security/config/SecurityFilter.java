package br.com.mutztech.agenda.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mutztech.agenda.security.domain.repository.UserRepository;
import br.com.mutztech.agenda.security.domain.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    // Filtro para validação do token, antes de realizar a requisão do usuario, esse
    // filtro vem antes de qualquer endpoint. EXCETO: Login e Register
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recuperaToken(request);
        if (token != null) {
            var email = tokenService.validandoToken(token);
            UserDetails user = userRepository.findByEmail(email);

            var autenticação = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticação);
        }  filterChain.doFilter(request, response);
    }

    // Recupera somente o token sem o Bearer
    private String recuperaToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        } else
            return authHeader.replace("Bearer ", "");
    }

}
