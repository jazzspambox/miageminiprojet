    <%@page import="java.util.ArrayList"%>
    <%@page import="com.paris5.miage1.trombinoscope.bean.Formation"%>
    <% int i=0; %>
    </head>
    <body>
        <div id="header">
            <div id="logo">
                <a href="trombinoscope?action=search" title="Home"><!-- --></a>
            </div>
            <div id="search" class="search" >
                <form ation="trombinoscope" method="post" onsubmit="checkSubmit('search_box','recherche')">
                    <input type="hidden" name="promo_label" value="" />
                    <input type="hidden" name="action" value="search"/>
                    <input id="search_box" type="text" name="serach_value" value="recherche" size="40" 
                           onblur="javascript:autoSelect(this, 'search_filter'); inputBehaviour('search_box', 'recherche');" 
                           onkeyup="javascript:autoSelect(this, 'search_filter');" 
                           onfocus="javascript:inputBehaviour('search_box', 'recherche');" />
                    <jsp:useBean id="formation" class="com.paris5.miage1.trombinoscope.bean.Formation" scope="page" />
                    <select id="search_promo" class="search_promo" name="search_promo">
                        <option value="0">toutes les formations</option>
                        <%
                        ArrayList<Formation> formations = (ArrayList<Formation>) request.getAttribute("formations");
                        for(i=0; i<formations.size(); i++){ 
                            formation = formations.get(i);
                            pageContext.setAttribute("formation", formation);
                        %>  
                        <option value="<jsp:getProperty name="formation" property="id" />-<jsp:getProperty name="formation" property="session" />">
                            <jsp:getProperty name="formation" property="label" />
                        </option>
                        <% } %>
                    </select>
                    <input type="submit" class="blue_btn submit_btn" value="ok" />
                    <div id="search_filter" class="search_filter">
                        <input type="radio" name="type" value="search_nom" /> Nom
                        <input type="radio" name="type" value="search_prenom" checked="checked" /> Prenom
                        <input type="radio" name="type" value="search_mobile" /> Telephonne mobile
                        <input type="radio" name="type" value="search_fixe" /> Telephonne fixe
                        <input type="radio" name="type" value="search_email" /> Email
                        <input type="radio" name="type" value="search_num_etudiant" /> Num-etudiant
                    </div>
                </form>
            </div>
            <div class="menu">
                <ul>
                    <li><a href="trombinoscope?action=search"><span>Accueil</span></a>
                    </li>
                    <li><a href="trombinoscope?action=show_user" ><span>Fiche</span></a>
                    </li>
                    <% if(session.getAttribute("ajout")!=null){ %>
                    <li><a href="trombinoscope?action=create_user" ><span>Ajouter utilisateur</span></a>
                    </li>
                    <% } %>
                    <li><a href="javascript:window.open('javadoc/install.pdf','trombi-documentation');" ><span>Aide</span></a>
                    </li>
                    <% if(session.getAttribute("documentation")!=null){ %>
                    <li><a href="javascript:window.open('javadoc/index.html','trombi-documentation');" ><span>Documentation</span></a>
                    </li>
                    <% } %>
                    <li id="disconnect"><a href="trombinoscope?action=deconnect">Deconnexion</a></li>
                </ul>
            </div>
        </div>
        <div id="clearer"><!-- clearer --></div>