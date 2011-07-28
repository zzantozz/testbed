package rds.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 4:36 PM
 */
public class MakeItImmutableClassLoader extends ClassLoader {
    public MakeItImmutableClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            ClassWriter writer = new ClassWriter(0);
            TraceClassVisitor tracer = new TraceClassVisitor(writer, new PrintWriter(System.out));
            MakeItImmutableClassAdapter immuter = new MakeItImmutableClassAdapter(tracer);
            ClassReader reader = new ClassReader(name);
            reader.accept(immuter, 0);
            byte[] bytes = writer.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            // TODO: Write me!
            throw new UnsupportedOperationException("Unwritten catch block");
        }
    }
}
