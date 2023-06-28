package in.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.ForgotPwd;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signUpPage(Model model) {

		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String handleSearch(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = userService.signUp(form);
		if (status) {
			model.addAttribute("successMsg", "Account created, Check Your Email");
		} else {
			model.addAttribute("errMsg", "Choose Unique Email");
		}
		return "signUp";

	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String Login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		String status = userService.Login(loginForm);
		if (status.contains("success")) {
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg", status);
		return "login";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm unlockformObj = new UnlockForm();
		unlockformObj.setEmail(email);

		model.addAttribute("unlock", unlockformObj);

		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockForm(@ModelAttribute("unlock") UnlockForm unlock, Model model) {

		if ((unlock.getNewPwd()).equals(unlock.getConfirmPwd())) {
			boolean status = userService.UnlockAccount(unlock);
			if (status) {
				model.addAttribute("successMsg", "Your Account Unlocked Successfully");
			} else {
				model.addAttribute("errMsg", "Given TempPwd is Incorrect ,Check your email");
			}
		} else {
			model.addAttribute("errMsg", "New password and Confirm password should be same");
		}

		return "unlock";
	}

	@GetMapping("/forgot")
	public String forgotPwdPage(Model model) {

		model.addAttribute("forgotPage", new ForgotPwd());
		return "forgotPwd";
	}

	@PostMapping("/forgot")
	public String forgotForm(@ModelAttribute("forgotPage") ForgotPwd form, Model model) {

		String status = userService.forgotPwd(form);
		if(status.contains("success")) {
			model.addAttribute("successMsg","Email sent to your Account");
		}else {
			model.addAttribute("errMsg",status);
		}
		
		return "forgotPwd";
	}
}
