package rds.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/26/11
 * Time: 7:29 PM
 */
public class ClassPrinter implements ClassVisitor {
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {
    }

    public void visitOuterClass(String owner, String name, String desc) {
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {
    }

    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }

    public static void main(String[] args) throws Exception {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.lang.Runnable");
        TraceClassVisitor cv = new TraceClassVisitor(new PrintWriter(System.out));
        System.out.println("***");
        cr.accept(cp, 0);
        System.out.println("***");
        cr.accept(cv, 0);
    }
}
