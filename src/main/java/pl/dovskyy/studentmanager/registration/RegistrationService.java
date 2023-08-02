package pl.dovskyy.studentmanager.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dovskyy.studentmanager.appuser.AppUser;
import pl.dovskyy.studentmanager.appuser.AppUserRole;
import pl.dovskyy.studentmanager.appuser.AppUserService;
import pl.dovskyy.studentmanager.registration.token.ConfirmationToken;
import pl.dovskyy.studentmanager.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {



    private final AppUserService appUserService;
    private EmailValidator emailValidator;
    private ConfirmationTokenService confirmationTokenService;

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

    @Transactional
    public String confirmToken(String token) {

        //take a look of the token exists. The method returns optional, so we need to handle it with exception.
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
