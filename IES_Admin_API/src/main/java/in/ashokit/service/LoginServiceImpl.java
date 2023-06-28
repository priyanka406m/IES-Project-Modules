package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.ForgotPwd;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.entity.IESUserEntity;
import in.ashokit.repo.UserDetailsRepository;
import in.ashokit.util.EmailUtils;
import in.ashokit.util.EncryptPwd;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDetailsRepository adminAndCaseWorkerRepository;

	/*
	 * 
	 * 
	 * @Autowired private PwdUtils pwdUtils;
	 * 
	 */
	@Autowired
	private HttpSession session;

	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private EncryptPwd encryptPwd;

	@Override
	public String Login(LoginForm form) {
		IESUserEntity entity = adminAndCaseWorkerRepository.findByUserEmailAndUserPazzword(form.getEmail(),
				form.getPwd());
		if (entity == null) {
			return "Invalid Credentials";
		}
		if ((entity.getActiveStatus()).equals("LOCKED")) {
			return "Your Account Locked";
		}
		return "success";
	}

	@Override
	public boolean UnlockAccount(UnlockForm form) throws Exception {
		IESUserEntity entity = adminAndCaseWorkerRepository.findByUserEmail(form.getEmail());

		if (entity.getUserPazzword().equals(form.getTempPwd())) {
			String pwd = EncryptPwd.encrypt(form.getNewPwd());
			entity.setUserPazzword(pwd);
			entity.setActiveStatus("UnLocked");
			adminAndCaseWorkerRepository.save(entity);
			return true;

		} else {
			return false;
		}

	}

	@Override
	public String forgotPwd(ForgotPwd form) throws Exception {
		IESUserEntity entity = adminAndCaseWorkerRepository.findByUserEmail(form.getEmail());
		if (entity == null) {
			return "Invalid Email Id";
		}
		String pwd = entity.getUserPazzword();
		String decryptPwd = EncryptPwd.decrypt(pwd);
		String to = form.getEmail();
		String subject = "Your Account Password";
		StringBuffer body = new StringBuffer();
		body.append("<h4>Here Is Your Pwd</h4>");
		body.append("Your Pwd: " + decryptPwd);
		body.append("<br/>");
		emailUtils.sendEmail(to, subject, body.toString());

		return "success";

	}

}
