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
    public String register(@ModelAttribute RegistrationRequest request) {
        registrationService.register(request);
        return "redirect:/";
    }

    @GetMapping(path = "confirm")
    public String confirm() {
        return "dupa";
    }

}
