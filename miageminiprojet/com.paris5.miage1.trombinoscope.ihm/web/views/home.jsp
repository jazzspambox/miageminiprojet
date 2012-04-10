<%@page import="com.paris5.miage1.trombinoscope.bean.Utilisateur"%>
<%@include file="header.html" %>
<%@include file="menu.jsp" %>
        <div id="page" class="page">
            <jsp:useBean id="filtre" class="com.paris5.miage1.trombinoscope.utils.Filtre" scope="request" />
            <div class="result_infos">
                <h2 id="search_label">Recherche <jsp:getProperty name="filtre" property="label" /> <jsp:getProperty name="filtre" property="recherche" /></h2>
                <span id="search_result"><jsp:getProperty name="filtre" property="resultCount" /> personne(s), <a href="mailto:katia@alger.fr">mail a tous</a>, <a href="javascript:void(0);">imprimer</a></span>
            </div>
            <br/>
            <jsp:useBean id="utilisateur" class="com.paris5.miage1.trombinoscope.bean.Utilisateur" scope="page" />
            <%
                ArrayList<Utilisateur> users = (ArrayList<Utilisateur>) request.getAttribute("users");
                for(i=0; i<users.size(); i++){ 
                    utilisateur = users.get(i);
                    pageContext.setAttribute("utilisateur", utilisateur);
            %>

            <!-- mini profil -->
            <div id="mini_profil">
                <div class="mini_photo">
                    <a href="trombinoscope?action=show_user&id=<jsp:getProperty name="utilisateur" property="login" />">
                        <img src="photos/<jsp:getProperty name="utilisateur" property="photoUrl" />"
                             title="<jsp:getProperty name="utilisateur" property="prenom" />
                             <jsp:getProperty name="utilisateur" property="nom" />" />
                    </a>
                    <% if(session.getAttribute("ajout")!=null){ %>
                    <a href="trombinoscope?action="><span class="remove_profil"></span></a>
                    <% } %>
                </div>
                <div class="nom">
                    <a href="javascript:void(0);"><jsp:getProperty name="utilisateur" property="prenom" /> 
                        <jsp:getProperty name="utilisateur" property="nom" /></a>
                </div>
                <span class="portable"><jsp:getProperty name="utilisateur" property="mobile" /></span>
                <span class="fixe"><jsp:getProperty name="utilisateur" property="telephone" /></span>
                <span class="email"><a href="mailto:katia@alger.fr"><jsp:getProperty name="utilisateur" property="email" /></a></span>
            </div>
            <% } %>
            <%@include file="pagination.jsp" %>
            <div id="clearer"><!-- clearer --></div>
        </div>
<%@include file="footer.html" %>