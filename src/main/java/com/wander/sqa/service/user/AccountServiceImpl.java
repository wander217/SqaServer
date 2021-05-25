package com.wander.sqa.service.user;

import com.wander.sqa.dao.AccountDAO;
import com.wander.sqa.dao.MemberDAO;
import com.wander.sqa.dto.user.AccountDTO;
import com.wander.sqa.dto.user.RoleDTO;
import com.wander.sqa.entity.user.Account;
import com.wander.sqa.entity.user.Member;
import com.wander.sqa.exception.PasswordNotMatchException;
import com.wander.sqa.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private JavaMailSender sender;
	private final PasswordEncoder passwordEncoder;
	
	public AccountServiceImpl() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	//Đăng nhập
	@Override
	@Transactional
	public AccountDTO findByUsername(AccountDTO a)
			throws UsernameNotFoundException, PasswordNotMatchException {
		Account account = this.accountDAO.findByUsername(a.getUsername())
				.orElseThrow(UsernameNotFoundException::new);
		if(passwordEncoder.matches(a.getPassword(), account.getPassword())) {
			return this.convertToDTO(account);
		}
		throw new PasswordNotMatchException();
	}

	//Chuyển về dạng dto
	private AccountDTO convertToDTO(Account account){
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setUsername(account.getUsername());
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(account.getRole().getId());
		roleDTO.setName(account.getRole().getName());
		accountDTO.setRole(roleDTO);
		return accountDTO;
	}

	//Quên mật khẩu
	@Override
	@Transactional
	public void doForgot(AccountDTO a) throws UsernameNotFoundException {
		Member member = this.memberDAO.findByUsername(a.getUsername())
				.orElseThrow(UsernameNotFoundException::new);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("sqanhom18@gmail.com");
		mail.setTo(member.getEmail());
		mail.setSubject("Xác nhặn quên mật khẩu");
		String newPassword = UUID.randomUUID().toString();
		Account account = member.getAccount();
		account.setPassword(this.passwordEncoder.encode(newPassword));
		mail.setText("Mật khẩu mới là:"+ newPassword);
		sender.send(mail);
	}
}