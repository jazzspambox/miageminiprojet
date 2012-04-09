    <%@page import="java.util.ArrayList"%>
    <%@page import="com.paris5.miage1.trombinoscope.bean.Formation"%>
    <% int i=0; %>
    </head>
    <body>
        <div id="header">
            <div id="logo">
                <a href="/" title="Home"><!-- --></a>
            </div>
            <div id="search" class="search" >
                <form ation="trombinoscope" method="post">
                    <input type="hidden" name="action" value="search"/>
                    <input id="search_box" type="text" name="serach_value" value="recherche" size="40" 
                           onblur="javascript:autoSelect(this); inputBehaviour('search_box', 'recherche');" 
                           onkeyup="javascript:autoSelect(this);" 
                           onfocus="javascript:inputBehaviour('search_box', 'recherche');" />
                    <jsp:useBean id="formation" class="com.paris5.miage1.trombinoscope.bean.Formation" scope="page" />
                    <select id="search_promo" class="search_promo" name="search_promo">
                        <option>toutes les formations</option>
                        <%
                        ArrayList<Formation> formations = (ArrayList<Formation>) request.getAttribute("formations");
                        for(i=0; i<formations.size(); i++){ 
                            formation = formations.get(i);
                            pageContext.setAttribute("formation", formation);
                        %>  
                        <option value="<jsp:getProperty name="formation" property="id" />">
                            <jsp:getProperty name="formation" property="label" />
                        </option>
                        <% } %>
                    </select>
                    <input type="submit" class="blue_btn submit_btn" value="ok"  />
                    <div id="search_filter" class="search_filter">
                        <input type="radio" name="search_type" value="1" /> Nom
                        <input type="radio" name="search_type" value="2" checked="checked" /> Prenom
                        <input type="radio" name="search_type" value="3" /> Telephonne mobile
                        <input type="radio" name="search_type" value="4" /> Telephonne fixe
                        <input type="radio" name="search_type" value="5" /> Email
                        <input type="radio" name="search_type" value="6" /> Num-etudiant
                    </div>
                </form>
            </div>
            <div class="menu">
                <ul>
                    <li><a href="trombinoscope?action=search"><span>Accueil</span></a>
                    </li>
                    <li><a href="trombinoscope?action=show_user" ><span>Fiche</span></a>
                    </li>
                    <li><a href="trombinoscope?action=create_user" ><span>Ajouter utilisateur</span></a>
                    </li>
                    <li><a href="javascript:void(0);" ><span>Aide</span></a>
                    </li>
                    <li><a href="javascript:void(0);" ><span>Documentation</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <div id="clearer"><!-- clearer --></div>