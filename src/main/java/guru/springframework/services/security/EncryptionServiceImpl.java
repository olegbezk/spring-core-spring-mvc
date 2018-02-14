package guru.springframework.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    private final StrongPasswordEncryptor strongEncryptor;

    @Autowired
    public EncryptionServiceImpl(final StrongPasswordEncryptor strongEncryptor) {
        this.strongEncryptor = strongEncryptor;
    }

    public String encryptString(final String input) {
        return strongEncryptor.encryptPassword(input);
    }

    public boolean checkPassword(final String plainPassword, final String encryptedPassword) {
        return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
