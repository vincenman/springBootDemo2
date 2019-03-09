package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.data.UserAccountRepository;
import com.luv2code.springboot.cruddemo.entity.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	//private EmployeeRepository employeeRepository;
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	public UserAccountServiceImpl(UserAccountRepository theUserAccountRepository) {
		userAccountRepository = theUserAccountRepository;
	}
	
	@Override
	public List<UserAccount> findAll() {
		return userAccountRepository.findAll();
	}

	@Override
	public UserAccount findById(int theId) {
		Optional<UserAccount> result = userAccountRepository.findById(theId);
		
		//Employee theEmployee = null;
		
		UserAccount userAccount = null;
		
		if (result.isPresent()) {
			userAccount = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find User Account id - " + theId);
		}
		
		return userAccount;
	}

	@Override
	public void save(UserAccount theUserAccount) {
		userAccountRepository.save(theUserAccount);
	}

	@Override
	public void deleteById(int theId) {
		userAccountRepository.deleteById(theId);
	}

}






