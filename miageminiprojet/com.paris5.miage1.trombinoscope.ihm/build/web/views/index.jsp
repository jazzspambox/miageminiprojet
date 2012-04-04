<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Trombinoscope</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta charset="utf-8" />
        <meta name="description" content="Retrouvez tous les etudiants miages paris descartes" />

        <!-- stykesheet -->
        <link rel="stylesheet" href="css/elements.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/authent.css" type="text/css" media="screen" />

        <!-- javascript -->
        <script src="javascript/jquery-1.7.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="authent_main">
            <div class="authent_form">
                <div class="logo">
                    <!-- -->
                </div>
                <div class="separator">
                    Trombinoscope Master Miage
                </div>
                <form action="trombinoscope" method="post">
                    <input type="hidden" name="action" value="authent" />
                    <div>
                        <input type="text" class="authent_input" name="login" title="Nom d'utilisateur" value="Nom d'utilisateur" />
                    </div>
                    <div>
                        <input type="password" class="authent_input" name="pwd" title="Mot de passe" value="Mot de passe" />
                    </div>
                    <div class="authent_submit">
                        <input type="submit" class="blue_btn submit_btn" value="Se connecter" />
                        <label class="authent_remember">
                            <input type="checkbox" value="1" name="remember_me" />
                            <span>Se souvenir de moi</span>
                        </label>
                    </div>
                </form>
            </div>
            <div id="forgot_pwd" class="forgot_pwd">
                <span>Vous avez oubli&eacute; votre <a id="forgot_bt" href="javascript:void(0)">mot de passe</a>?</span>
                <div id="sendPwdBlock" class="sendPwdBlock" style="display: none;">
                    <div id="forgot_form"><span>Indiquez l'adresse email associ&eacute;e a votre compte pour recevoir vos codes d'acc&ecirc;s.</span>
                        <form action="" method="post" >
                            <input type="text" class="authent_email_send" name="email_send" title="adresse email" value="adresse email" size="50" />
                            <input type="submit" class="blue_btn submit_btn" value="ok"  />                          
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $("#forgot_bt").click(function () {
                if ($("#sendPwdBlock").is(":hidden")) {
                    $("#sendPwdBlock").slideDown("fast");
                } else {
                    $("#sendPwdBlock").slideUp();
                }
            });
        </script>
    </body>
</html>