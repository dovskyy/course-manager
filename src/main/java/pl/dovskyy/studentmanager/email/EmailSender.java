package pl.dovskyy.studentmanager.email;

public interface EmailSender {
    void send(String to, String emailContent);
}
