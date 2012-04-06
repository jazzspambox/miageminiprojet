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
            <div id="photoContainer">
                <div id="photoLoader">
                    <img id="profilPhoto" src="images/photo-indisponible.png" alt="portrait" />
                </div>
                <form id="file" action="trombinoscope" method="post" enctype="multipart/form-data" >
                    <input type="hidden" name="action" value="send_photo" />
                    <input type="file" name="file" class="blue_btn submit_btn" />
                </form>   
            </div>
            <form id="dataUser" action="trombinoscope" method="post" enctype="multipart/form-data" >
                <input type="hidden" name="action" value="add_user" />
                <input type="hidden" id="x" name="x" value="" />
                <input type="hidden" id="y" name="y" value="" />
                <input type="hidden" id="x2" name="x2" value="" />
                <input type="hidden" id="y2" name="y2" value="" />
                <input type="hidden" id="h" name="y2" value="" />
                <input type="hidden" id="w" name="y2" value="" />
                <div id="previewContainer">
                    <img id="preview" src="images/mini-photo-indisponible.png" />
                </div>
                <div id="data_authent">
                    <h4>Authentification</h4>
                    <label for="nom">- idantifiant : *</label>
                    <input type="text" name="login" value ="" class="validate[required,funcCall[validate2fields],custom[onlyLetter],length[8,10]]" id="login" /><br/>
                    <label for="nom">- mot de passe : *</label>
                    <input type="text" name="password" value="" class="validate[required,length[6,11]] text-input" type="password" name="password"  id="password" /><br/>
                    <label for="nom">- groupe : *</label>
                    <select id="liste_groupes" class="validate[required] liste_groupes" name="groupe">
                        <option value="">Etudiant</option>
                    </select>
                </div>
                <div id="data_trombi">
                    <h4>Trombinoscope</h4>
                    <label for="nom">*</label>
                    <select id="sex" name="sex" class="validate[required] sex">
                        <option value="0">Homme</option>
                        <option value="1">Femme</option>
                    </select>
                    <select id="search_promo" class="search_promo" name="search_promo">
                        <%
                        for(i=0; i<formations.size(); i++){ 
                            formation = formations.get(i);
                            pageContext.setAttribute("formation", formation);
                        %>  
                        <option value="<jsp:getProperty name="formation" property="id" />">
                            <jsp:getProperty name="formation" property="label" />
                        </option>
                        <% } %>
                    </select><br/>
                    <label for="nom">- nom : *</label><input type="text" name="nom" value="" class="validate[optional,funcCall[validate2fields],custom[onlyLetter],length[0,100]]" id="firstname"/><br/>
                    <label for="nom">- prenom : *</label><input type="text" name="prenom" value="" class="validate[optional,funcCall[validate2fields],custom[onlyLetter],length[0,100]]" id="lastname"/><br/>
                    <label for="nom">- num etudiant : </label><input type="text" name="num_etudiant" value="" /><br/>
                    <label for="nom">- e-mail *:</label><input type="text" name="email" value="" class="validate[optional,custom[email]]" id="email" /><br/>
                    <label for="nom">- telephone portable :</label><input type="text" name="portable" value="" class="validate[optional,custom[telephone]]" id="portable" /><br/>
                    <label for="nom">- telephone mobile :</label><input type="text" name="mobile" value="" class="validate[optional,custom[telephone]]" id="mobile" /><br/>
                    <input type="button" name="cancel" value="annuler" class="grey_btn submit_btn"  />
                    <input type="submit" name="portable" value="ajouter" class="submit blue_btn submit_btn"  />
                </div>
            </form>
            <div id="clearer"><!-- clearer --></div>
        </div>
<%@include file="footer.html" %>