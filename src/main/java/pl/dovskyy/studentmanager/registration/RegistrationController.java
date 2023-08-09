package pl.dovskyy.studentmanager.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ModelAndView showRegistrationForm() {
        ModelAndView mav = new ModelAndView("registration-form");
        RegistrationRequest request = new RegistrationRequest();
        mav.addObject("request", request);
        return mav;
    }

    @PostMapping("/registerUser")
    public ModelAndView register(@ModelAttribute RegistrationRequest request) {
        String token = registrationService.register(request);
        ModelAndView mav = new ModelAndView("tokenView");
        mav.addObject("token", token);
        return mav;
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam String token) {
        registrationService.confirmToken(token);
        return "tokenConfirmed";
    }

}
