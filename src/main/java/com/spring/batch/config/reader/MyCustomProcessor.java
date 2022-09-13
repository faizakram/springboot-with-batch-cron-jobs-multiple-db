package com.spring.batch.config.reader;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.batch.config.dto.EmployeeDTO;
import com.spring.batch.secondary.Manager;
import com.spring.batch.secondary.repository.ManagerRepository;

@Component
public class MyCustomProcessor implements ItemProcessor<EmployeeDTO, Manager> {
	
	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public Manager process(EmployeeDTO emp) throws Exception {
		System.out.println("MyBatchProcessor : Processing data : 1 " + emp);
		Optional<Manager> optionalManager = managerRepository.findByEmail(emp.getEmail());
		Manager manager = null;
		if (optionalManager.isPresent()) {
			System.out.println("MyBatchProcessor : Processing data IF: " + emp);
			manager = optionalManager.get();
		} else {
			System.out.println("MyBatchProcessor : Processing data Else: " + emp);
			manager = new Manager();
		}
		manager.setName(emp.getName().toUpperCase());
		manager.setSalary(emp.getSalary());
		manager.setEmail(emp.getEmail());
		return manager;
	}
}
