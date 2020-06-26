package bg.softuni.judge.web.filters;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import bg.softuni.judge.domain.models.binding.LoggedInUser;
import bg.softuni.judge.enums.Roles;

public class BaseAuthorizationFilter implements Filter {
	private Set<Roles> allowedRoles;

	public BaseAuthorizationFilter() {
	}

	public BaseAuthorizationFilter(final Set<Roles> allowedRoles) {
		this.allowedRoles = allowedRoles;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null
				|| isAccessForbidden(((LoggedInUser) session.getAttribute("user")).getRoles())) {
			try {
				if (session != null)
					session.invalidate();
			} catch (IllegalStateException e) {

			}
			UriComponentsBuilder.newInstance();
			resp.sendRedirect(UriComponentsBuilder.fromUriString("/user/login")
					.queryParam("redirectURL", req.getRequestURI()).toUriString());
			return;
		}

		chain.doFilter(request, response);

	}

	private boolean isAccessForbidden(Set<Roles> userRoles) {
		for (Roles role : userRoles) {
			if (allowedRoles.contains(role)) {
				return false;
			}
		}
		return true;
	}

}
