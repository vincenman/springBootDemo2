package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.UserAccount;
import com.luv2code.springboot.cruddemo.service.UserAccountService;

@RestController
@RequestMapping("/api")
public class UserAccountRestController {

	private UserAccountService userAccountService;
	
	@Autowired
	public UserAccountRestController(UserAccountService theUserAccountService) {
		userAccountService = theUserAccountService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/userAccounts")
	public List<UserAccount> findAll() {
		return userAccountService.findAll();
	}

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/userAccounts/{userAccountId}")
	public UserAccount getUserAccount(@PathVariable int userAccountId) {
		
		//Employee theEmployee = employeeService.findById(employeeId);
		
		UserAccount theUserAccount = userAccountService.findById(userAccountId);
		
		if (theUserAccount == null) {
			throw new RuntimeException("theUserAccount id not found - " + userAccountId);
		}
		
		return theUserAccount;
	}
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/userAccounts")
	public UserAccount addUserAccount(@Valid @RequestBody UserAccount theUserAccount) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theUserAccount.setId(0);
		
		userAccountService.save(theUserAccount);
		
		return theUserAccount;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/userAccounts")
	public UserAccount updateUserAccount(@RequestBody UserAccount theUserAccount) {
		
		userAccountService.save(theUserAccount);
		
		return theUserAccount;
	}
	
	
	@PutMapping("/userAccount/{userAccountId}/balance/{amount}")
	public UserAccount makeTransaction(@PathVariable int userAccountId, @PathVariable double amount) {
		
		UserAccount theUserAccount = getUserAccount(userAccountId);
		
		double currentAmount = theUserAccount.getBalance();
		
		theUserAccount.setBalance(currentAmount + amount);
		
		userAccountService.save(theUserAccount);
		
		return theUserAccount;
	}
	

	
}










