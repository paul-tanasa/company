package emp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import emp.entities.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmployeesRowMapper() {
		return jdbcTemplate.query("select id,cnp,first_name, last_name,to_char(date_of_birth,'dd-MM-YYYY'),address,married,blacklisted from employee", new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rownumber) throws SQLException {
				Employee e = new Employee();
				e.setId(rs.getInt(1));
				e.setCnp(rs.getLong(2));
				e.setFirstName(rs.getString(3));
				e.setLastName(rs.getString(4));
				e.setDateOfBirth(rs.getString(5));
				e.setAddress(rs.getString(6));
				e.setMarried(Boolean.valueOf(rs.getString(7)));
				e.setBlackListed(Boolean.valueOf(rs.getString(8)));
				return e;
			}
		});
	}

	public Employee getEmployeeById(Integer id) {
		return jdbcTemplate.queryForObject("select id,cnp,first_name, last_name,to_char(date_of_birth,'dd-MM-YYYY'),address,married,blacklisted from employee where id=?", new Object[] { id },
				new RowMapper<Employee>() {

					public Employee mapRow(ResultSet rs, int rownumber) throws SQLException {
						Employee e = new Employee();
						try{
							e.setId(rs.getInt(1));
							e.setCnp(rs.getLong(2));
							e.setFirstName(rs.getString(3));
							e.setLastName(rs.getString(4));
							e.setDateOfBirth(rs.getDate(5).toString());
							e.setAddress(rs.getString(6));
							e.setMarried(Boolean.valueOf(rs.getString(7)));
							e.setBlackListed(Boolean.valueOf(rs.getString(8)));
						}
						catch(SQLException ex){
							ex.printStackTrace();
						}
						return e;
					}
				});
	}

	public void add(Employee employee) {
		String sql = "insert into EMPLOYEE (id, cnp, first_Name, last_Name, date_Of_Birth, address, married, blackListed) values (employee_seq.nextval, ?, ?, ?, TO_DATE(?, 'dd-MM-YYYY'), ?, ?, ?)";
		jdbcTemplate.update(sql, employee.getCnp(), employee.getFirstName(), employee.getLastName(),
				employee.getDateOfBirth(), employee.getAddress(), employee.isMarried(), employee.isBlackListed());
	}
	
	public void update(Employee employee) {
		String sql = "update EMPLOYEE set cnp=?, first_Name=?, last_Name=?, date_Of_Birth=TO_DATE(?, 'dd-MM-YYYY'), address=?, married=?, blackListed=? where id=?";
		jdbcTemplate.update(sql, employee.getCnp(), employee.getFirstName(), employee.getLastName(),
				employee.getDateOfBirth(), employee.getAddress(), employee.isMarried(), employee.isBlackListed(), employee.getId());
	}
	
	public void delete(Integer id) {
		String sql = "delete from EMPLOYEE where id=?";
		jdbcTemplate.update(sql, id);
	}

}
