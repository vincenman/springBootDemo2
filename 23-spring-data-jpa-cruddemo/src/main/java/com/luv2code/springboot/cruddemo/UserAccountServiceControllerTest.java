package com.luv2code.springboot.cruddemo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.luv2code.springboot.cruddemo.entity.UserAccount;


public class UserAccountServiceControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void getUserAccountsList() throws Exception {
      String uri = "/api/userAccounts";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      UserAccount[] productlist = super.mapFromJson(content, UserAccount[].class);
      assertTrue(productlist.length > 0);
   }
   
   @Test
   public void getUserAccountsListById() throws Exception {
      String uri = "/api/userAccounts/1";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      UserAccount userAccount = super.mapFromJson(content, UserAccount.class);
      assertTrue(userAccount !=null );
   }
   
   
   @Test
   public void addUserAccount() throws Exception {
      String uri = "/api/userAccounts";
      UserAccount userAccount = new UserAccount();
     // product.setId("3");
     // product.setName("Ginger");
      userAccount.setFirstName("Test Account");
      userAccount.setLastName("Test Account Last Name");
      userAccount.setBalance(5000);
      
      String inputJson = super.mapToJson(userAccount);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
     // String content = mvcResult.getResponse().getContentAsString();
    //  assertEquals(content, "Product is created successfully");
   }
   @Test
   public void updateUserAcocunt() throws Exception {
      String uri = "/api/userAccounts";
      UserAccount product = new UserAccount();
      product.setId(1);
      product.setFirstName("Test First Nam Updated!");
      product.setLastName("Test Last Name updated!");
      product.setBalance(1000);
      String inputJson = super.mapToJson(product);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
     // assertEquals(content, "Product is updated successsfully");
   }
   
   @Test
   public void makeTransaction() throws Exception {
      String uri = "/api/userAccount/2/balance/-3";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      //String content = mvcResult.getResponse().getContentAsString();
     // assertEquals(content, "Product is deleted successsfully");
   }
}