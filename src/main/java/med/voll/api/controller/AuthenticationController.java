package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.AuthenticationDataDto;
import med.voll.api.entity.UsersEntity;
import med.voll.api.infra.security.JWTDataTokenDto;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid AuthenticationDataDto auth){
        var authToken = new UsernamePasswordAuthenticationToken(auth.user(), auth.password());
        Authentication authentication = manager.authenticate(authToken);

        var token = tokenService.generateToken((UsersEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTDataTokenDto(token));
    }
}
