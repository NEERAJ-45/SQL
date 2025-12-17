import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.google.gson.Gson;

public class SampleSecure {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // constants
    private static final String AES = "AES";
    private static final String PKCS12 = "PKCS12";
    private static final String BC_PROVIDER = "BC";
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String RSA_TRANSFORMATION = "RSA";

    private PrivateKey privateKey;

    public static void main(String[] args) {
        try {
        	SampleSecure client = new SampleSecure();
            client.callSecureApi("http://localhost:8080/iADV/rest/dataVault/getDataByRefKey");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void callSecureApi(String urlStr) throws Exception {
        String jsonRequest = "{\"refKey\":\"736482562316\"}";
        System.out.println("Plain Request = " + jsonRequest);

        // Generate AES key
        SecretKey aesKey = generateAesKey();

        // Create HMAC
        byte[] hmacBytes = calculateHmac(jsonRequest);
        String hmac = Base64.encodeBase64String(hmacBytes);

        // Encrypt payload with AES
        String encryptedPayload = Base64.encodeBase64String(encryptData(jsonRequest.getBytes(), aesKey));

        // Encrypt AES key with RSA public key
        PublicKey publicKey = loadPublicKey("E:\\temp\\INTEGRA-ADV-PUBLIC-CERT.pem.cer");
        String secureHeader = Base64.encodeBase64String(encryptAesKeyWithRsa(aesKey.getEncoded(), publicKey));

        // Prepare payload JSON
        Payload payload = new Payload();
        payload.setRequest(encryptedPayload);
        payload.setHmac(hmac);
        String payloadStr = new Gson().toJson(payload);

        System.out.println("Encrypted Request JSON = " + payloadStr);

        // Send request
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("USER_NAME", "BOOT_MIG_USER");
        conn.setRequestProperty("API_KEY", "592388b0-0be2-11ef-b29a-9b25da7d2efe");
        conn.setRequestProperty("SECURE", secureHeader);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payloadStr.getBytes());
        }

        // Read response
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String responseStr = sb.toString();
        System.out.println("Encrypted Response JSON = " + responseStr);

        // Parse response
        Payload response = new Gson().fromJson(responseStr, Payload.class);
        String encryptedResponse = response.getResponse();

        // Decrypt AES key from response header
        String respHeader = conn.getHeaderField("SECURE");
        this.privateKey = loadPrivateKey("E:\\temp\\payloadencryptkeys\\INTEGRA-ADV-PRIVATE-KEY.p12", "int123$%^", "1");
        byte[] decryptedKeyBytes = decryptAesKeyWithRsa(Base64.decodeBase64(respHeader));
        SecretKey responseAesKey = new SecretKeySpec(decryptedKeyBytes, AES);

        // Decrypt response payload
        String plainResponse = new String(decryptData(Base64.decodeBase64(encryptedResponse), responseAesKey));
        System.out.println("Decrypted Response: " + plainResponse);

        // Verify HMAC
        byte[] generatedHmac = calculateHmac(plainResponse);
        if (!Arrays.equals(generatedHmac, Base64.decodeBase64(response.getHmac()))) {
            throw new RuntimeException("HMAC mismatch!");
        }

        System.out.println("HMAC verified successfully!");
    }

    // ===== AES helpers =====
    private static byte[] encryptData(byte[] data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION, BC_PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private static byte[] decryptData(byte[] data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION, BC_PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private static SecretKey generateAesKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES, BC_PROVIDER);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    // ===== RSA helpers =====
    private byte[] decryptAesKeyWithRsa(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION, BC_PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
        return cipher.doFinal(data);
    }

    private static byte[] encryptAesKeyWithRsa(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION, BC_PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    // ===== Key loading =====
    private static PublicKey loadPublicKey(String certificatePath) throws Exception {
        try (FileInputStream fis = new FileInputStream(new File(certificatePath))) {
            X509Certificate publicCert = (X509Certificate)
                    CertificateFactory.getInstance("X.509", "BC").generateCertificate(fis);
            return publicCert.getPublicKey();
        }
    }

    private static PrivateKey loadPrivateKey(String filePath, String password, String alias) throws Exception {
        try (InputStream in = new FileInputStream(filePath)) {
            KeyStore ks = KeyStore.getInstance(PKCS12);
            ks.load(in, password.toCharArray());
            PrivateKeyEntry entry = (PrivateKeyEntry) ks.getEntry(alias,
                    new KeyStore.PasswordProtection(password.toCharArray()));

            if (entry == null) {
                Enumeration<String> aliases = ks.aliases();
                if (aliases.hasMoreElements()) {
                    String fallbackAlias = aliases.nextElement();
                    entry = (PrivateKeyEntry) ks.getEntry(fallbackAlias,
                            new KeyStore.PasswordProtection(password.toCharArray()));
                }
            }
            return entry.getPrivateKey();
        }
    }

    // ===== HMAC =====
    private static byte[] calculateHmac(String data) throws Exception {
        return MessageDigest.getInstance("SHA-256").digest(data.getBytes());
    }

    // ===== Payload model =====
    public static class Payload {
        private String request;
        private String response;
        private String hmac;
        private String errorCode;

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getHmac() {
            return hmac;
        }

        public void setHmac(String hmac) {
            this.hmac = hmac;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }
}
