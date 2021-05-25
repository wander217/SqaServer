package com.wander.sqa.service.user;

import com.wander.sqa.dto.user.AccountDTO;
import com.wander.sqa.exception.PasswordNotMatchException;
import com.wander.sqa.exception.UsernameNotFoundException;

public interface AccountService{
	AccountDTO findByUsername(AccountDTO a) throws UsernameNotFoundException,PasswordNotMatchException;
	void doForgot(AccountDTO a) throws UsernameNotFoundException;
}