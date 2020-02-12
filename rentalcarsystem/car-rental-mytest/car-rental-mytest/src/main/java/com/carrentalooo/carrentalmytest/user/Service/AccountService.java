package com.carrentalooo.carrentalmytest.user.Service;

import com.carrentalooo.carrentalmytest.domain.Account;
import com.carrentalooo.carrentalmytest.user.DAO.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class AccountService {

	@Autowired
	AccountDAO accountDAO;

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public void addAccount(Account account){
//		account.setAccountType(AccountType.CUSTOMER);
		//account.setAccountType(AccountType.ADMIN);
		account.setActive(true);
		accountDAO.save(account);
	}
	public void updateAccount(Account account){
		accountDAO.setAccountByAccountId(account.getUsername(), account.getPassword(), account.getActive(), account.getAccountType(), account.getAccountId());
		 
	}
	public void deleteAccount(Integer AccountId) {
		accountDAO.delete(AccountId);
		
	}
	
	public Account findByUsername(String username){
		return accountDAO.findByUsername(username);
	}
	//MD5
	   public String MD5(String md5){
	        java.security.MessageDigest md;
	        
	        try {
	            md = java.security.MessageDigest.getInstance("MD5");
	            byte[] array=md.digest(md5.getBytes());
	            StringBuffer sb=new StringBuffer();
	            for (int i = 0; i < array.length; ++i) {
	                  sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	               }
	                return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null; 
	        
	    }
}
