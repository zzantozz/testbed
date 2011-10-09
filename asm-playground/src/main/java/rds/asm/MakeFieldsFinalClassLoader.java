package rds.asm;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 4:36 PM
 */
public class MakeFieldsFinalClassLoader extends ClassLoader {
    public MakeFieldsFinalClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = new MakeFieldsFinalBytecodeManipulator().getModifiedByteCode(name);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Unable to read class", e);
        }
    }
}
