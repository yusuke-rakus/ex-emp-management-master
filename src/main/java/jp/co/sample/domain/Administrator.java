package jp.co.sample.domain;
/**
 * Administratorのドメインクラス
 * @author yusukematsumoto
 *
 */
public class Administrator {

	private Integer id;
	private String name;
	private String emailAddress;
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
		return "Administrator [id=" + id + ", name=" + name + ", emailAddress=" + emailAddress + ", password="
				+ password + "]";
	}

}
