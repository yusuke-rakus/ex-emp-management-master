package jp.co.sample.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER =
			new BeanPropertyRowMapper<>(Administrator.class);
	
	/**
	 * 管理者の更新。
	 * @param admin
	 */
	public void insert(Administrator admin) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(admin);
		if(admin.getId() == null) {
			String sql = "INSERT INTO administrators(name, mail_address, password) "
					+ "VALUES(:name, :mailAddress, :password)";
			template.update(sql, param);
		} else {
			String sql = "UPDATE administrators "
					+ "SET name=:name, mail_address=:mailAddress, password=:password "
					+ "WHERE id=:id";
			template.update(sql, param);
		}
	}
	
	/**
	 *  mailAddress, passwordが合致するレコードがあれば
	 *  そのAdministratorインスタンスを返却する
	 * @param mailAddress
	 * @param password
	 * @return Administratorインスタンス
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id, name, mail_address, password "
				+ "FROM administrators "
				+ "WHERE mail_address = :mailAddress AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("mailAddress", mailAddress).addValue("password", password);
		List<Administrator> adminList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(adminList .size() == 0) {
			return null;
		}
		return adminList.get(0);
	}
}
