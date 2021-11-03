package jp.co.sample.form;

/**
 * 管理者のログイン情報を受け取るフォーム
 * 
 * @author yusukematsumoto
 *
 */
public class LoginForm {

	private String emailAddress;
	private String password;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [emailAddress=" + emailAddress + ", password=" + password + "]";
	}
}
