package rest.entity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vrg on 08/06/16.
 */
public class DigestBuilder {
    private final MessageDigest digest;

    public DigestBuilder() {
        try {
            this.digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public DigestBuilder update(String value) {
        digest.update("|".getBytes());
        digest.update(value.getBytes());
        return this;
    }

    public DigestBuilder update(Object value) {
        return update(String.valueOf(value));
    }

    public String build() {
        try {
            return new BigInteger(digest.digest()).toString(10);
        } finally {
            digest.reset();
        }
    }
}
