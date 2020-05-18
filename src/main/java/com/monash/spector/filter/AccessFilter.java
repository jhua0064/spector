package com.monash.spector.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AccessFilter implements Filter {
    @Override
    public void destroy() {

    }

    /**
     * set temporary password for website
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        if("/access".equals(path)){
            filterChain.doFilter(request,response);
            return;
        }

        HttpSession session = request.getSession();
        String pwd = request.getParameter("pwd");
        if(pwd != null && pwd.equals("E12")){
            session.setAttribute("accessAllowed",true);
            filterChain.doFilter(request,response);
        }else{
            Boolean access = (Boolean) session.getAttribute("accessAllowed");
            if(access != null && access == true){
                filterChain.doFilter(request,response);
            }else{
                response.sendRedirect("/access");
            }
        }


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
