package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.binding.ForgotPwd;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockForm;

@Service
public interface LoginService {
	
	public String Login(LoginForm form);
	
	public boolean UnlockAccount(UnlockForm form) throws Exception;
	
	public String forgotPwd(ForgotPwd email) throws Exception;
}
