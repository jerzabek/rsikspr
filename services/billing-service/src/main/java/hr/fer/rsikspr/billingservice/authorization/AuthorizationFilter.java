package hr.fer.rsikspr.billingservice.authorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static hr.fer.rsikspr.billingservice.authorization.Response.getSerializedErrorBody;
import static hr.fer.rsikspr.billingservice.common.Constants.USER_AUTHENTICATION_HEADER;

@Slf4j(topic = "Security filter")
public class AuthorizationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    String customHeader = request.getHeader(USER_AUTHENTICATION_HEADER);

    if (customHeader == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json");
      response.getWriter().write(getSerializedErrorBody("Unauthorized"));

      return;
    }

    filterChain.doFilter(request, response);
  }
}
