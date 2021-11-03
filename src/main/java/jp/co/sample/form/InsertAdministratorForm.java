package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

public class InsertAdministratorForm {
	@NotBlank(message="")
	private String name;
	@NotBlank(message="")
	private String emailAddress;
	@NotBlank(message="")
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
