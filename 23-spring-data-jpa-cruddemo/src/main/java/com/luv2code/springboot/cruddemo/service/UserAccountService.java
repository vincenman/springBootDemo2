package com.luv2code.springboot.cruddemo.service;

import java.util.List;


import com.luv2code.springboot.cruddemo.entity.UserAccount;

public interface UserAccountService {

	public List<UserAccount> findAll();
	
	public UserAccount findById(int theId);
	
	public void save(UserAccount theUserAccount);
	
	public void deleteById(int theId);
	
}
