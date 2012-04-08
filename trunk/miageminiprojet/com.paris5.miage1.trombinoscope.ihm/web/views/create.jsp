<%@page import="java.util.Iterator"%>
<%@page import="com.paris5.miage1.trombinoscope.bean.Groupe"%>
<%@include file="header.html" %>        
        <!-- image resize -->
        <link rel="stylesheet" href="css/ajouter_style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" media="screen" />
        <script src="javascript/jquery.Jcrop.min.js" type="text/javascript"></script>

        <!-- image upload -->
        <script src="javascript/imageUploader.js" type="text/javascript"></script>
        
        <!-- form validation -->
        <link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8" />
        <script src="javascript/jquery.validationEngine-fr.js" type="text/javascript"></script>
	<script src="javascript/jquery.validationEngine.js" type="text/javascript"></script>

<%@include file="menu.jsp" %> 
        <script type="text/javascript">
            // photo uploader
            $().ready(function() {
                $('#file').postImage();
            });
            
            // form validation
            $(document).ready(function() {
                $("#dataUser").validationEngine()
           });
        </script>
        <div id="page" class="page">
            <jsp:useBean id="notification" class="com.paris5.miage1.trombinoscope.utils.Notification" scope="request" >
            <br/>
            <div class="<jsp:getProperty name="notification" property="level" />">
                <p>Notification (
                    <jsp:getProperty name="notification" property="id" />) :
                    <jsp:getProperty name="notification" property="message" /> une erreur interne c'est produite, l'ajout d'utilisateur a echouer.</p>
            </div>
            <br/>
            </jsp:useBean>
            <div id="photoContainer">
                <div id="photoLoader">
                    <img id="profilPhoto" src="images/photo-indisponible.png" alt="portrait" />
                </div>
                <form id="file" action="trombinoscope" method="post" enctype="multipart/form-data" >
                    <input type="hidden" name="action" value="send_photo" />
                    <input type="file" name="file" class="blue_btn submit_btn" />
                </form>   
            </div>
            <form id="dataUser" action="trombinoscope" method="post" >
                <input type="hidden" name="action" value="add_user" />
                <input type="hidden" id="x" name="x" value="" />
                <input type="hidden" id="y" name="y" value="" />
                <input type="hidden" id="x2" name="x2" value="" />
                <input type="hidden" id="y2" name="y2" value="" />
                <input type="hidden" id="h" name="h" value="" />
                <input type="hidden" id="w" name="w" value="" />
                <input type="hidden" id="photo_url" name="photo_url" value="" />
                <div id="previewContainer">
                    <img id="preview" src="images/mini-photo-indisponible.png" />
                </div>
                <jsp:useBean id="profil" class="com.paris5.miage1.trombinoscope.bean.Utilisateur" scope="request" />
                <div id="data_authent">
                    <h4>Authentification</h4>
                    <label for="login">- idantifiant : *</label>
                    <input type="text" name="login" value ="<jsp:getProperty name="profil" property="nom" />" class="validate[required,custom[onlyAuthorized],length[6,10]]" id="login" /><br/>
                    <label for="password">- mot de passe : *</label>
                    <input name="password" value="<jsp:getProperty name="profil" property="password" />" class="validate[required,length[6,10]] text-input" type="password" id="password" /><br/>
                    <label for="liste_groupes">- groupe : *</label>
                    <jsp:useBean id="groupe" class="com.paris5.miage1.trombinoscope.bean.Groupe" scope="page" />
                    <select id="liste_groupes" class="validate[required] liste_groupes" name="groupe" />
                        <%
                        ArrayList<Groupe> grps = (ArrayList<Groupe>) request.getAttribute("groupes");
                        for(i=0; i<grps.size(); i++){
                            groupe = grps.get(i);
                            pageContext.setAttribute("groupe", groupe);
                        %>  
                        <option value="<jsp:getProperty name="groupe" property="nom" />">
                            <jsp:getProperty name="groupe" property="nom" />
                        </option>
                        <% } %>
                    </select>
                </div>
                <div id="data_trombi">
                    <h4>Trombinoscope</h4>
                    <label for="nom">*</label>
                    <select id="sex" name="sex" class="validate[required] sex">
                        <option value="0">Homme</option>
                        <option value="1">Femme</option>
                    </select>
                    <jsp:useBean id="sess" class="com.paris5.miage1.trombinoscope.bean.Formation" scope="page" />
                    <select id="search_promo" class="search_promo" name="search_promo">
                        <%
                        ArrayList<Formation> fsession = (ArrayList<Formation>) request.getAttribute("selectSession");
                        Iterator it = fsession.iterator();
                        while(it.hasNext()){ 
                            sess = (Formation) it.next();
                            pageContext.setAttribute("sess", sess);
                        %>  
                        <option value="<jsp:getProperty name="sess" property="id" />-<jsp:getProperty name="sess" property="session" />">
                            <jsp:getProperty name="sess" property="label" />
                        </option>
                        <% } %>
                    </select><br/>
                    <label for="nom">- nom : *</label><input type="text" name="nom" value="<jsp:getProperty name="profil" property="nom" />" class="validate[optional,funcCall[validate2fields],custom[onlyLetter],length[0,100]]" id="nom"/><br/>
                    <label for="prenom">- prenom : *</label><input type="text" name="prenom" value="<jsp:getProperty name="profil" property="prenom" />" class="validate[optional,funcCall[validate2fields],custom[onlyLetter],length[0,100]]" id="prenom"/><br/>
                    <label for="num_etudiant">- num etudiant : </label><input type="text" name="num_etudiant" value="<jsp:getProperty name="profil" property="numEtudiant" />" id="num_etudiant" /><br/>
                    <label for="email">- e-mail *:</label><input type="text" name="email" value="<jsp:getProperty name="profil" property="email" />" class="validate[optional,custom[email]]" id="email" /><br/>
                    <label for="mobile">- telephone portable :</label><input type="text" name="mobile" value="<jsp:getProperty name="profil" property="mobile" />" class="validate[optional,custom[telephone]]" id="mobile" /><br/>
                    <label for="fixe">- telephone fixe :</label><input type="text" name="fixe" value="<jsp:getProperty name="profil" property="telephone" />" class="validate[optional,custom[telephone]]" id="fixe" /><br/>
                    <input type="button" name="cancel" value="annuler" class="grey_btn submit_btn"  />
                    <input type="submit" name="ajouter" value="ajouter" class="submit blue_btn submit_btn"  />
                </div>
            </form>
            <div id="clearer"><!-- clearer --></div>
        </div>
<%@include file="footer.html" %>