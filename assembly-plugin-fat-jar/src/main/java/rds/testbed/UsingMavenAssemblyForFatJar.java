package rds.testbed;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 10/16/11
 * Time: 11:04 AM
 */
public class UsingMavenAssemblyForFatJar {
    public static void main(String[] args) throws IOException {
        InputStream aClasspathResource =
                UsingMavenAssemblyForFatJar.class.getResourceAsStream("/a-classpath-resource.txt");
        IOUtils.copy(aClasspathResource, System.out);
    }
}
