package pl.dovskyy.studentmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dovskyy.studentmanager.appuser.AppUser;
import pl.dovskyy.studentmanager.appuser.AppUserRepository;
import pl.dovskyy.studentmanager.appuser.AppUserRole;

/**
 * Inicjalizator danych - tworzy domyślne konto administratora przy starcie aplikacji
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(AppUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createDefaultAdminUser();
        createDefaultTestUser();
    }

    private void createDefaultAdminUser() {
        String adminEmail = "admin@admin.com";

        // Sprawdź czy użytkownik admin już istnieje
        if (appUserRepository.findByEmail(adminEmail).isEmpty()) {
            AppUser admin = new AppUser(
                    "Admin",
                    "Administrator",
                    adminEmail,
                    passwordEncoder.encode("admin123"), // Hasło: admin123
                    AppUserRole.ADMIN
            );

            // Ustaw konto jako włączone i odblokowane
            admin.setEnabled(true);
            admin.setLocked(false);

            appUserRepository.save(admin);

            System.out.println("=== DOMYŚLNE KONTO ADMINISTRATORA ===");
            System.out.println("Email: " + adminEmail);
            System.out.println("Hasło: admin123");
            System.out.println("Rola: ADMIN");
            System.out.println("=====================================");
        } else {
            System.out.println("Konto administratora już istnieje: " + adminEmail);
        }
    }

    private void createDefaultTestUser() {
        String userEmail = "user@test.com";

        // Sprawdź czy użytkownik testowy już istnieje
        if (appUserRepository.findByEmail(userEmail).isEmpty()) {
            AppUser testUser = new AppUser(
                    "Test",
                    "User",
                    userEmail,
                    passwordEncoder.encode("user123"), // Hasło: user123
                    AppUserRole.USER
            );

            // Ustaw konto jako włączone i odblokowane
            testUser.setEnabled(true);
            testUser.setLocked(false);

            appUserRepository.save(testUser);

            System.out.println("=== DOMYŚLNE KONTO UŻYTKOWNIKA ===");
            System.out.println("Email: " + userEmail);
            System.out.println("Hasło: user123");
            System.out.println("Rola: USER");
            System.out.println("==================================");
        } else {
            System.out.println("Konto użytkownika testowego już istnieje: " + userEmail);
        }
    }
}
