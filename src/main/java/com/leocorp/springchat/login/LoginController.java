package com.leocorp.springchat.login;

import com.leocorp.springchat.login.dto.LoginInfo;
import com.leocorp.springchat.security.service.JWTService;
import com.leocorp.springchat.user.dto.UserCredential;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;
//    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
//    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
//
//    public LoginController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Operation(summary = "Login a user")
//    @PostMapping("/login")
//    public void login(@Valid @NotNull @RequestBody UserCredential credential, HttpServletRequest request, HttpServletResponse response) {
//        Authentication token  =
//                UsernamePasswordAuthenticationToken.unauthenticated(credential.username(), credential.password());
//        Authentication authentication =
//                this.authenticationManager.authenticate(token);
//
//        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
//        context.setAuthentication(authentication);
//        securityContextHolderStrategy.setContext(context);
//        securityContextRepository.saveContext(context, request, response);
//    }

    private final JWTService jwtService;

    public LoginController(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginInfo getToken(@Valid @NotNull @RequestBody UserCredential credential) {
                Authentication token  =
                UsernamePasswordAuthenticationToken.unauthenticated(credential.username(), credential.password());
        Authentication authentication =
                this.authenticationManager.authenticate(token);

        String jwtToken = jwtService.generateToken(authentication);
        return new LoginInfo(token.getName(), jwtToken);
    }

//    @Operation(summary = "Logout a user")
//    @ApiResponse(responseCode = "204", description = "Logout successful")
    @GetMapping("/logoutSuccess")
    private String logoutSuccess() {
        return "redirect:/";
    }
}
