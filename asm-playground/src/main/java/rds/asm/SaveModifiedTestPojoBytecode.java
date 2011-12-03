package rds.asm;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 10/9/11
 * Time: 1:11 AM
 */
public class SaveModifiedTestPojoBytecode {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String className = TestPojo.class.getName();
        byte[] modifiedByteCode = new MakeFieldsFinalBytecodeManipulator()
                .withNewClassName(className.replace("TestPojo", "ModifiedTestPojo"))
                .getModifiedByteCode(className);
        URI testPojoClassFileUri = TestPojo.class.getResource("TestPojo.class").toURI();
        URI modifiedUri = URI.create(testPojoClassFileUri.toString().replace("TestPojo", "ModifiedTestPojo"));
        File file = new File(modifiedUri);
        System.out.println("Writing class file to " + file.getAbsolutePath());
        FileUtils.writeByteArrayToFile(file, modifiedByteCode);
    }
}
