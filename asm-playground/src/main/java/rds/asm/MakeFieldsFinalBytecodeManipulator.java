package rds.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 10/9/11
 * Time: 1:05 AM
 */
class MakeFieldsFinalBytecodeManipulator {
    private String newClassName;

    public MakeFieldsFinalBytecodeManipulator withNewClassName(String newClassName) {
        this.newClassName = newClassName;
        return this;
    }

    public byte[] getModifiedByteCode(String className) throws IOException {
        ClassWriter writer = new ClassWriter(0);
        TraceClassVisitor tracer = new TraceClassVisitor(writer, new PrintWriter(System.out));
        MakeFieldsFinalClassAdapter makeFieldsFinalClassAdapter = new MakeFieldsFinalClassAdapter(tracer, newClassName);
        ClassReader reader = new ClassReader(className);
        reader.accept(makeFieldsFinalClassAdapter, 0);
        return writer.toByteArray();
    }
}
