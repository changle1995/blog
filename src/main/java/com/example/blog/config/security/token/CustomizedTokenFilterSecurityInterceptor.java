package com.example.blog.config.security.token;

import com.example.blog.enumeration.HeaderNameEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 自定义spring security Token 验证过滤器
 * Author: changle
 * Date: 2018/5/11
 * Time: 11:51
 */
@Order(2000)
@Service("customizedTokenFilterSecurityInterceptor")
@EnableConfigurationProperties(TokenConfigProperties.class)
public class CustomizedTokenFilterSecurityInterceptor extends AbstractAuthenticationProcessingFilter {

    private List<? extends RequestMatcher> ignoreAuthenticationRequestMatcherList;

    /**
     * 父类无空构造方法,需主动调用父类构造方法,并在此设置需要拦截的url路径
     * 默认经过此过滤器的请求不再调用下一步的过滤器,需手动设置继续过滤器链
     */
    public CustomizedTokenFilterSecurityInterceptor(TokenConfigProperties tokenConfigProperties) {
        super(new AntPathRequestMatcher(tokenConfigProperties.getRequiresAuthenticationRequestUrl()));
        super.setContinueChainBeforeSuccessfulAuthentication(true);
        setIgnoreRequestMatcherList(tokenConfigProperties.getIgnoreAuthenticationRequestUrls());
    }

    /**
     * 设置忽略的路径
     */
    public final void setIgnoreRequestMatcherList(List<String> ignoreRequestUrlList) {
        this.ignoreAuthenticationRequestMatcherList = Optional.ofNullable(ignoreRequestUrlList).map(urlList -> urlList.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList())).orElse(null);
    }

    /**
     * 判断请求头部是否含有合法的token,若没有或与sessionID不同都清除用户信息,则返回401错误
     * 若用户未登陆则直接返回匿名的Authentication表示通过,因为此过滤器仅处理token,登录操作为其他过滤器处理
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return authentication;
        } else {
            String token = httpServletRequest.getHeader(HeaderNameEnum.USER_TOKEN.getName());
            HttpSession httpSession = httpServletRequest.getSession(false);
            if (httpSession.getId().equals(token)) {
                return authentication;
            } else {
                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
                throw new BadCredentialsException("token已过期,请重新登录");
            }
        }
    }

    /**
     * 默认会调用父类中的successHandler进行跳转,用空方法覆盖以让请求正常通过
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    }

    /**
     * 需注入AuthenticationManager,否则在父类中afterPropertiesSet方法会校验不允许AuthenticationManager为空
     */
    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    /**
     * 此方法决定哪些url需要验证
     */
    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return super.requiresAuthentication(request, response) && !this.ignoreAuthentication(request, response);
    }

    protected boolean ignoreAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (this.ignoreAuthenticationRequestMatcherList == null) {
            return false;
        }
        for (RequestMatcher requestMatcher : this.ignoreAuthenticationRequestMatcherList) {
            if (requestMatcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

}
