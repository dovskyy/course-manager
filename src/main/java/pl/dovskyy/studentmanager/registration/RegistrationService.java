package pl.dovskyy.studentmanager.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.appuser.AppUser;
import pl.dovskyy.studentmanager.appuser.AppUserRole;
import pl.dovskyy.studentmanager.appuser.AppUserService;

@Service
@AllArgsConstructor
public class RegistrationService {



    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (isValidEmail == false) {
            throw new IllegalArgumentException("email not valid");
        }

        AppUser appUser = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER);

        return appUserService.signUpUser(appUser);
    }
}
