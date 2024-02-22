package com.sms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.dto.AccountListItemDTO;
import com.sms.model.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	
	@Modifying
	@Query("UPDATE Account a SET a.password = :password WHERE a.id = :id")
	void changePassword(@Param("id") Long id, @Param("password") String newPassword);
	
	@Query("SELECT new com.sms.dto.AccountListItemDTO(a.id, a.name, a.email) from Account a")
	List<AccountListItemDTO> findAllAccounts();

	@Modifying
	@Query("UPDATE Account a SET a.status = 0 WHERE a.id = :id")
	void banAccount(@Param("id") Long accountId);
	
	@Modifying
	@Query("UPDATE Account a SET a.email = :email, a.phone = :phone, a.address = :address"
			+ " WHERE a.id = :id")
	void modifyAccount(@Param("id") Long id, @Param("email") String email, @Param("phone") String phone, 
						@Param("address") String address);
	
	
	Optional<Account> findByEmailOrPhone(String email, String phone);
	
	Optional<Account> findByEmail(String email);
}
