package com.luv2code.springboot.cruddemo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.entity.UserAccount;



public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	// that's it ... no need to write any code LOL!
	
}
