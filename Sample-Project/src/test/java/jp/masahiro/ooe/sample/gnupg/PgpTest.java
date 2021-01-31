package jp.masahiro.ooe.sample.gnupg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Security;
import java.util.Collection;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.junit.Test;

@SuppressWarnings("all")
public class PgpTest {

    private PGPSecretKeyRing keyRing;

    private void getInstanceKeyRing() throws FileNotFoundException {
        if (keyRing == null) {
            FileInputStream is = new FileInputStream("gpgfilename.gpg");

            is.close();
        }
    }

    @Test
    public void testNullToBlankSelializer() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        PGPSecretKeyRingCollection secretKeyRing = new PGPSecretKeyRingCollection(
                (Collection<PGPSecretKeyRing>) new FileInputStream(new File("test-files/secring.gpg")));
        PGPSecretKeyRing pgpSecretKeyRing = (PGPSecretKeyRing) secretKeyRing.getKeyRings().next();
        PGPSecretKey secretKey = pgpSecretKeyRing.getSecretKey();
        PGPPrivateKey privateKey = secretKey.extractPrivateKey("mypassword".toCharArray(), "BC");

        System.out.println(privateKey.getKey().getAlgorithm());
        System.out.println(privateKey.getKey().getFormat());

        PGPObjectFactory pgpF = new PGPObjectFactory( new FileInputStream(new File("test-files/test-file.txt.gpg")),
                null);
        Object pgpObj = pgpF.nextObject();
        PGPEncryptedDataList encryptedDataList = (PGPEncryptedDataList) pgpObj;

        Iterator objectsIterator = encryptedDataList.getEncryptedDataObjects();

        PGPPublicKeyEncryptedData publicKeyEncryptedData = (PGPPublicKeyEncryptedData) objectsIterator.next();
        InputStream inputStream = publicKeyEncryptedData.getDataStream(privateKey, "BC");

	}

}
