package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.PasswordResetDTO;
import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.ProfileService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class SignInController {
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private ProfileService profileService;


    public SignInController() {
        System.out.println("created no-arg constructor in SignInController...");
    }

    @PostMapping("/signIn")
    public String checkSignIn(@RequestParam String email, @RequestParam String password, Model model,
                              HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        Optional<SignupDTO> signupDTOOptional = this.signUpService.findByEmailAndPassword(email, password);

        if (signupDTOOptional.isPresent()) {
            SignupDTO signupDTO = signupDTOOptional.get();
            model.addAttribute("dto", signupDTO);
            model.addAttribute("readOnly", "disable");
            int count = signupDTO.getLogin_count();
            signupDTO.setLock_account(0);
            if (count == 0) {
                signupDTO.setLogin_count(1);
                this.signUpService.update(signupDTO);
                return "PasswordReset";
            } else {
                int num = count + 1;
                signupDTO.setLogin_count(num);
                this.signUpService.update(signupDTO);
                HttpSession httpSession = request.getSession();
                List<ProfileDTO> profileDTO = profileService.findDatasById(signupDTOOptional.get().getId());
                httpSession.setAttribute("dto", signupDTO);
                if (!profileDTO.isEmpty()) {
                    profileDTO.forEach(data -> {
                        if ("Active".equals(data.getStatus())) {
                            String ref = "/profile/" + data.getImageName();
                            httpSession.setAttribute("profileDTO", ref);
                        }
                    });
                } else {
                    httpSession.removeAttribute("profileDTO");
                }
                return "UserHomePage";
            }
        } else {
            Optional<SignupDTO> optionalSignupDTO = this.signUpService.findByemail(email);
            if (optionalSignupDTO.isPresent()) {
                SignupDTO signupDTO = optionalSignupDTO.get();
                int lockAccount = signupDTO.getLock_account();
                if (lockAccount < 2) {
                    int lockAc = lockAccount + 1;
                    signupDTO.setLock_account(lockAc);
                    this.signUpService.update(signupDTO);
                    int chance = 3 - lockAc;
                    model.addAttribute("msg", "You have only " + chance + " attempts left. Enter valid email or password.");
                    return "SignIn";
                } else {
                    signupDTO.setPassword(null);
                    signupDTO.setUserPassword(null);
                    signupDTO.setUpdatedDate(LocalDateTime.now());
                    signupDTO.setLock_account(3);  // Lock the account
                    this.signUpService.update(signupDTO);
                    model.addAttribute("msg", "Your account is locked... please reset your password.");
                    return "FindByEmail";
                }
            } else {
                model.addAttribute("msg", "No account found with this email. Please check and try again.");
                return "SignIn";
            }
        }
    }


    @PostMapping("/resetPassword")
    public String resetUserPassword(@Valid PasswordResetDTO passwordResetDTO, Model model) {
        System.out.println("newPassword : " + passwordResetDTO.getPassword());
        System.out.println("confirmNewPassword : " + passwordResetDTO.getConfirmNewPassword());
        Optional<SignupDTO> optionalSignupDTO = this.signUpService.findByEmailAndPassword(passwordResetDTO.getEmail(),
                passwordResetDTO.getPassword());

        if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())) {
            optionalSignupDTO.get().setUserPassword(passwordResetDTO.getNewPassword());
            System.out.println("optionalSignupDTO in controller...: " + optionalSignupDTO);
            boolean updateValue = this.signUpService.update(optionalSignupDTO.get());
            if (updateValue) {
                System.out.println("data saved for email: " + optionalSignupDTO.get().getEmail());
                model.addAttribute("msg", "Password Reset Success..");
                return "PasswordReset";
            } else {
                System.out.println("Data not saved!!!!!!!!");
            }
        } else {
            model.addAttribute("errorMsg", "NewPassword and Confirm Password should be same");
            return "PasswordReset";
        }
        return "PasswordReset";
    }

    @GetMapping("/fetchByEmail")
    public String getDataByEmail(@RequestParam String email, Model model) {
        Optional<SignupDTO> optionalSignupDTO = this.signUpService.findByEmailForReset(email);
        if (optionalSignupDTO.isPresent()) {
            model.addAttribute("mail", "Enter password sent by email..");
            model.addAttribute("dto", optionalSignupDTO.get());
            return "ResetPasswordAnyTime";
        } else {
            model.addAttribute("msg", "Enter valid email..");
            return "FindByEmail";
        }

    }

    @PostMapping("/resetPasswordAnyTime")
    public String resetUserPassword2(@Valid PasswordResetDTO passwordResetDTO, Model model) {
        System.out.println("newPassword : " + passwordResetDTO.getPassword());
        System.out.println("confirmNewPassword : " + passwordResetDTO.getConfirmNewPassword());
        Optional<SignupDTO> optionalSignupDTO = this.signUpService.findByEmailAndPassword(passwordResetDTO.getEmail(),
                passwordResetDTO.getPassword());

        if (optionalSignupDTO.isPresent()) {
            SignupDTO signupDTO = optionalSignupDTO.get();
            if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())) {
                signupDTO.setUserPassword(passwordResetDTO.getConfirmNewPassword());
                System.out.println("optionalSignupDTO in controller...: " + optionalSignupDTO);
                boolean updateValue = this.signUpService.update(signupDTO);
                if (updateValue) {
                    System.out.println("data saved for email: " + signupDTO.getEmail());
                    model.addAttribute("msg", "Password Reset Success..");
                    return "ResetPasswordAnyTime";
                } else {
                    System.out.println("Data not saved!!!!!!!!");
                }
            } else {
                model.addAttribute("errorMsg", "NewPassword and Confirm Password should be same");
                return "ResetPasswordAnyTime";
            }
        } else {
            model.addAttribute("errorMsg", "Invalid email or password");
            return "ResetPasswordAnyTime";
        }
        return "ResetPasswordAnyTime";
    }
}