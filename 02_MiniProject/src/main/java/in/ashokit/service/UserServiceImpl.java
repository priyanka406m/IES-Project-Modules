package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.ForgotPwd;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.UserDtlsRepo;
import in.ashokit.util.EmailUtils;
import in.ashokit.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo repo;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private HttpSession session;

	@Override
	public String Login(LoginForm form) {
		UserDtlsEntity entity = repo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		if (entity == null) {
			return "Invalid Credentials";
		}
		if ((entity.getAccStatus()).equals("LOCKED")) {
			return "Your Account Locked";
		}

		session.setAttribute("userId", entity.getUserId());
		return "success";
	}

	@Override
	public boolean signUp(SignUpForm form) {

		UserDtlsEntity user = repo.findByEmail(form.getEmail());

		if (user != null) {
			return false;

		}

		// copy data from binding object to entity obj

		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);

		// generate random pwd

		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd);

		// set account status as locked

		entity.setAccStatus("LOCKED");

		// Insert record into table

		repo.save(entity);

		// send email to unlock account

		String to = form.getEmail();
		String subject = "Unlock Your Account";
		StringBuffer body = new StringBuffer();
		body.append("<h1>Use below temporary password to unlock your account</h1>");
		body.append("Temporary Pwd: " + tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8181/unlock?email=" + to + "\">Click Here To Unlcok Your Account</a>");
		emailUtils.sendEmail(to, subject, body.toString());
		return true;
	}

	@Override
	public boolean UnlockAccount(UnlockForm form) {
		UserDtlsEntity entity = repo.findByEmail(form.getEmail());
		if ((entity.getPwd()).equals(form.getTempPwd())) {
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("UnLocked");
			repo.save(entity);
			return true;

		} else {
			return false;
		}

	}

	@Override
	public String forgotPwd(ForgotPwd form) {
		UserDtlsEntity entity = repo.findByEmail(form.getEmail());
		if (entity == null) {
			return "Invalid Email Id";
		}

		String pwd = entity.getPwd();
		String to = form.getEmail();
		String subject = "Your Account Password";
		StringBuffer body = new StringBuffer();
		body.append("<h4>Here Is Your Pwd</h4>");
		body.append("Your Pwd: " + pwd);
		body.append("<br/>");
		emailUtils.sendEmail(to, subject, body.toString());

		return "success";
	}



}
