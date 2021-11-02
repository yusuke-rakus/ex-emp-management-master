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

import jp.co.sample.domain.Employee;
/**
 * 従業員テーブルからオブジェクトを取得するリポジトリ
 * @author yusukematsumoto
 *
 */
@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER =
			new BeanPropertyRowMapper<>(Employee.class);
	
	/**
	 * 全従業員情報を取得する
	 * @return
	 */
	public List<Employee> findAll(){
		String sql = "SELECT * from employees";
		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}
	/**
	 * idから従業員情報を主屋する 
	 * @param id
	 * @return Employeeインスタンス
	 */
	public Employee load(Integer id) {
		String sql = "SELECT * FROM employees "
				+ "WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}
	/**
	 * 従業員情報の更新・追加をする
	 * @param employee
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		if(employee.getId() == null) {
			String sql = "INSERT INTO employees "
					+ "VALUES(:name,:image,:gender,:hire_date"
							+ ",:mail_address,:zip_code,:address"
							+ ",:telephone,:salary,:characteristics);";
			template.update(sql, param);
		} else {
			String sql = "UPDATE employees "
					+ "SET name=:name, image=:image, gender=:gender"
					+ ", hire_date=:hireDate, mail_address=:mailAddress"
					+ ", zip_code=:zipCode, address=:address, telephone=:telephone"
					+ ", salary=:salary, characteristics=:characteristics";
			template.update(sql, param);
		}
	}
}
