/* ------------------------------------------------------------------------
 
    Licensed under the Educational Community License version 1.0

    This Original Work, including software, source code, documents,
    or other related items, is being provided by the copyright holder(s)
    subject to the terms of the Educational Community License. By
    obtaining, using and/or copying this Original Work, you agree that you
    have read, understand, and will comply with the following terms and
    conditions of the Educational Community License:

    Permission to use, copy, modify, merge, publish, distribute, and
    sublicense this Original Work and its documentation, with or without
    modification, for any purpose, and without fee or royalty to the
    copyright holder(s) is hereby granted, provided that you include the
    following on ALL copies of the Original Work or portions thereof,
    including modifications or derivatives.

 ------------------------------------------------------------------------ */

package com.paris5.miage1.trombinoscope.utils;

import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.UtilisateurDAO;
import java.io.IOException;
import java.sql.SQLException;
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
 * Classe permettant de tracer les activite d authentification sur le site
 * @author Mourad, Saliou, Idir
 */
public class Tracker implements Filter {

    private FilterConfig config;

    @Override
    /**
     * initialisation du filtre
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    /**
     * execution du filtre
     */
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
