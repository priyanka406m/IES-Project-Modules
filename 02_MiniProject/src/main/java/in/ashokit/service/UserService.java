package in.ashokit.service;

import in.ashokit.binding.ForgotPwd;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;

public interface UserService {
	
	public String Login(LoginForm form);
	
	public boolean signUp(SignUpForm form);
	
	public boolean UnlockAccount(UnlockForm form);
	
	public String forgotPwd(ForgotPwd email);
	

}
