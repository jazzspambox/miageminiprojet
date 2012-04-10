<%@include file="header.html" %>
        <link rel="stylesheet" href="css/ajouter_style.css" type="text/css" media="screen" />       
<%@include file="menu.jsp" %>
        <div id="page" class="page">
            
            <div id="profil">
                <div class="mini_photo">
                    <img id="preview" src="photos/<jsp:getProperty name="profil" property="photoUrl" />" />
                </div>
                <jsp:useBean id="profil" class="com.paris5.miage1.trombinoscope.bean.Utilisateur" scope="request" />
                <span class="nom"><jsp:getProperty name="profil" property="prenom" /> <jsp:getProperty name="profil" property="nom" /></span>
                <span class="portable"><jsp:getProperty name="profil" property="mobile" /></span>
                <span class="fixe"><jsp:getProperty name="profil" property="telephone" /></span>
                <span class="NumEtudiant"><jsp:getProperty name="profil" property="numEtudiant" /></span>
                <span class="NumEtudiant"><jsp:getProperty name="profil" property="dateCreation" /></span>
                <span class="email"><jsp:getProperty name="profil" property="email" /></span>
            </div>
            <div id="clearer"><!-- clearer --></div>
        </div>
<%@include file="footer.html" %>