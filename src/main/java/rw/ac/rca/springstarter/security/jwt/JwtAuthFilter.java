package rw.ac.rca.springstarter.security.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import rw.ac.rca.springstarter.exceptions.TokenException;
import rw.ac.rca.springstarter.security.user.UserSecurityDetails;
import rw.ac.rca.springstarter.serviceImpls.UserSecurityDetailServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserSecurityDetailServiceImpl userSecurityDetail;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        JwtUserInfo jwtUserInfo = null;
        String jwtToken = null;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);

        try {
            jwtUserInfo = jwtUtils.decodeToken(jwtToken);
            if (jwtUserInfo.getEmail() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserSecurityDetails userSecurityDetails = (UserSecurityDetails) userSecurityDetail.loadUserByUsername(jwtUserInfo.getEmail());
                if (jwtUtils.isTokenValid(jwtToken, userSecurityDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userSecurityDetails, jwtToken, userSecurityDetails.getGrantedAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()+"getWriter error");
            throwErrors(request, response, e);
        }
        filterChain.doFilter(request,response);
    }

    private void throwErrors(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        if (!response.isCommitted()) {
            TokenException exception = new TokenException(e.getMessage());
            response.setStatus(exception.getResponseEntity().getStatusCodeValue());
            response.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(exception.getResponseEntity().getBody()));
        }
    }

}
