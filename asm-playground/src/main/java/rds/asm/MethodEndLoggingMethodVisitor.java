package rds.asm;

import org.objectweb.asm.*;

import java.io.PrintStream;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 5:47 PM
 */
public class MethodEndLoggingMethodVisitor extends MethodAdapter {
    private MethodVisitor wrappedVisitor;

    public MethodEndLoggingMethodVisitor(MethodVisitor wrappedVisitor) {
        super(wrappedVisitor);
        this.wrappedVisitor = wrappedVisitor;
    }

    @Override
    public void visitEnd() {
        Label label = new Label();
        wrappedVisitor.visitLabel(label);
        wrappedVisitor.visitLineNumber(1234, label);
        wrappedVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", Type.getDescriptor(PrintStream.class));
        wrappedVisitor.visitLdcInsn("End of method");
        wrappedVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        wrappedVisitor.visitEnd();
/*
        L1
         LINENUMBER 14 L1
         GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
         LDC "Instantiating"
         INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
*/
    }
}
