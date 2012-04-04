<%@include file="header.html" %>
        <link rel="stylesheet" href="css/ajouter_style.css" type="text/css" media="screen" />       
<%@include file="menu.jsp" %>
        <div id="page" class="page">
            <div id="photoLoader">
                    <img id="profilPhoto" src="images/photo-indisponible.png" alt="portrait" />
                </div>
            <form id="dataUser" action="trombinoscope" method="post" enctype="multipart/form-data" >
                <div id="previewContainer">
                    <img id="preview" src="images/mini-photo-indisponible.png" />
                </div>
                <div id="data_trombi">
                    <h4>Trombinoscope</h4>
                    <label for="nom">*</label>
                    <select id="sex" class="sex" name="sex">
                        <option value="0">Homme</option>
                        <option value="1">Femme</option>
                    </select><br/>                    
                    <label for="nom">- nom : *</label><input type="text" name="nom" value="" /><br/>
                    <label for="nom">- prenom : *</label><input type="text" name="prenom" value="" /><br/>
                    <label for="nom">- num etudiant : </label><input type="text" name="nom" value="" /><br/>
                    <label for="nom">- e-mail :</label><input type="text" name="email" value="" /><br/>
                    <label for="nom">- adresse :</label><input type="text" name="adresse" value="" /><br/>
                    <label for="nom">- telephone portable :</label><input type="text" name="portable" value="" /><br/>
                    <label for="nom">- telephone mobile :</label><input type="text" name="portable" value="" /><br/>
                    <input type="button" name="cancel" value="annuler" class="grey_btn submit_btn"  />
                    <input type="submit" name="portable" value="ajouter" class="blue_btn submit_btn"  />
                </div>
            </form>
            <div id="clearer"><!-- clearer --></div>
        </div>
<%@include file="footer.html" %>