package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


public class InsertAdministratorForm {
	
	@NotBlank(message="氏名を入力してください")
	private String name;
	
	@Email(message="メールアドレスを正しく入力してください")
	@NotBlank(message="メールアドレスを正しく入力してください")
	private String emailAddress;
	
	@Length(min=8, max=20, message="8文字以上20文字以内で入力してください")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
		return "InsertAdministratorForm [name=" + name + ", emailAddress=" + emailAddress + ", password=" + password
				+ "]";
	}
}
