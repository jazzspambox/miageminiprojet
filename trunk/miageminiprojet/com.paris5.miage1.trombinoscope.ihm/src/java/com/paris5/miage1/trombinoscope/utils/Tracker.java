/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.utils;

import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.UtilisateurDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

/**
 *
 * @author mourad
 */
public class Tracker implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Utilisateur user = null;
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session != null) {
            user = (Utilisateur) session.getAttribute("user");
        }
        
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        if (user == null && login!=null && pwd!=null) {
            UserAgent userAgent = UserAgent.parseUserAgentString(((HttpServletRequest) request).getHeader("User-Agent"));
            Version browser_version = userAgent.getBrowserVersion();
            Browser browser = userAgent.getBrowser();
            OperatingSystem os = userAgent.getOperatingSystem();
            UtilisateurDAO dao = new UtilisateurDAO();
            try {
                dao.Track(
                        login, 
                        pwd, 
                        browser.toString(),
                        browser_version.getVersion().toString(), 
                        os.toString(), 
                        os.getManufacturer().toString(), 
                        request.getRemoteAddr()
                        );
            } catch (SQLException ex) {
                //Logger.getLogger(Tracker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
