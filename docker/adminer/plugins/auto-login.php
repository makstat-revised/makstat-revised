<?php
class AdminerAutoLogin {
  public function loginForm() {
    if (getenv('ADMINER_AUTOLOGIN') !== 'true') {
      return null; // disable autologin
    }

    $type = getenv('ADMINER_DB_DRIVER');
    if ($type == 'mysql') { $type = 'server'; }
    $server = getenv('ADMINER_DB_SERVER');
    $database = getenv('ADMINER_DB_NAME');
    $user = getenv('ADMINER_DB_USER');
    $password = getenv('ADMINER_DB_PASSWORD');
    echo '<span id="form-marker"/>';
    echo '<input type="hidden" name="auth[driver]" value="'.$type.'">';
    echo '<input type="hidden" name="auth[server]" value="'.$server.'">';
    echo '<input type="hidden" name="auth[username]" value="'.$user.'">';
    echo '<input type="hidden" name="auth[password]" value="'.$password.'">';
    echo '<input type="hidden" name="auth[db]" value="'.$database.'">';
    $nonce = get_nonce();
    echo <<<SCRIPT
      <script nonce="$nonce">
        window.addEventListener('DOMContentLoaded', (e) => {
          document.getElementById("form-marker").parentElement.submit()
        })
      </script>
    SCRIPT;
    return true; // non null value hides original form
	}
}
?>
