package com.wander.sqa.controller;

import com.wander.sqa.dto.user.AccountDTO;
import com.wander.sqa.exception.PasswordNotMatchException;
import com.wander.sqa.exception.UsernameNotFoundException;
import com.wander.sqa.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/account")
public class AccountController{
	@Autowired
	private AccountService accountService;

	//Đăng nhập
	@PostMapping(path = "/login")
	public ResponseEntity<AccountDTO> findByUsername(@RequestBody AccountDTO accountDTO)
			throws UsernameNotFoundException, PasswordNotMatchException{
		AccountDTO ans = accountService.findByUsername(accountDTO);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}

	//Lấy mật khẩu đã mất
	@PostMapping(path = "/forgot")
	public ResponseEntity<String> doForgot(@RequestBody AccountDTO accountDTO)
			throws UsernameNotFoundException{
		accountService.doForgot(accountDTO);
		return new ResponseEntity<>("Gửi mail thành công!", HttpStatus.OK);
	}
}