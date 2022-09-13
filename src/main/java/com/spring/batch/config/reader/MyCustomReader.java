package com.spring.batch.config.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.spring.batch.config.dto.EmployeeDTO;

@Component
public class MyCustomReader extends JdbcCursorItemReader<EmployeeDTO> implements ItemReader<EmployeeDTO> {

	@Value("$(spring.batch.job.steps}")
	private String batchStep;

	public MyCustomReader(@Autowired DataSource primaryDataSource) {
		setDataSource(primaryDataSource);
		setSql("select u.name as name, u.email as email, u.mobile as mobile, e.salary as salary from my_schema.employee e join my_schema.app_user u on e.id=u.id where e.time > "
				+ "(SELECT bse.last_updated FROM my_schema.batch_step_execution bse where bse.step_name=" + batchStep + " and bse.exit_code='COMPLETED' "
				+ "ORDER BY step_execution_id DESC limit 1)::timestamp without time zone");

		setRowMapper(new EmployeeRowMapper());
	}

	public class EmployeeRowMapper implements RowMapper<EmployeeDTO> {
		@Override
		public EmployeeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeDTO employee = new EmployeeDTO();
			employee.setName(rs.getString("name"));
			employee.setEmail(rs.getString("email"));
			employee.setMobileNo(rs.getString("mobile"));
			employee.setSalary(rs.getInt("salary"));
			return employee;
		}
	}
}