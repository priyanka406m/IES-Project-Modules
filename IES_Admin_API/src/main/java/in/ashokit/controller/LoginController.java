package in.ashokit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.ForgotPwd;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.service.LoginService;

@RestController
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String loginPage() {
		// model.addAttribute("loginForm", new LoginForm());
		return "login success";
	}

	@PostMapping("/login")
	public String Login(@RequestBody LoginForm loginForm) {
		String status = loginService.Login(loginForm);
		Integer role = loginForm.getRoleId();
		logger.info(status);
		if (status.contains("success")) {
			if (role==1 ){
				return "loading dashboard";
			}
			return "loading dashboard";
		}
		return "login error";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestBody String email) {
		UnlockForm unlockformObj = new UnlockForm();
		unlockformObj.setEmail(email);

		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockForm(@RequestBody UnlockForm unlock) throws Exception {

		if ((unlock.getNewPwd()).equals(unlock.getConfirmPwd())) {
			boolean status = loginService.UnlockAccount(unlock);
			if (status) {
				return "successMsg, Your Account Unlocked Successfully";
			} else {
				return "error";
			}
		} else {
			return "New password and Confirm password should be same";
		}

		// return "unlock";
	}

	@GetMapping("/forgot")
	public String forgotPwdPage(Model model) {

		model.addAttribute("forgotPage", new ForgotPwd());
		return "forgotPwd";
	}

	@PostMapping("/forgot")
	public String forgotForm(@ModelAttribute("forgotPage") ForgotPwd form, Model model) throws Exception {

		String status = loginService.forgotPwd(form);
		if (status.contains("success")) {
			model.addAttribute("successMsg", "Email sent to your Account");
		} else {
			model.addAttribute("errMsg", status);
		}

		return "forgotPwd";
	}

}
