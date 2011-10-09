package rds.asm;

import org.objectweb.asm.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 4:03 PM
 */
public class MakeFieldsFinalClassAdapter extends ClassAdapter {
    private ClassVisitor wrappedVisitor;
    private String newClassName;

    public MakeFieldsFinalClassAdapter(ClassVisitor wrappedVisitor) {
        this(wrappedVisitor, null);
    }

    public MakeFieldsFinalClassAdapter(ClassVisitor wrappedVisitor, String newClassName) {
        super(wrappedVisitor);
        this.wrappedVisitor = wrappedVisitor;
        this.newClassName = newClassName;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if (newClassName == null) {
            wrappedVisitor.visit(version, access, name, signature, superName, interfaces);
        } else {
            wrappedVisitor.visit(version, access, newClassName, signature, superName, interfaces);
        }
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return wrappedVisitor.visitField(access | Opcodes.ACC_FINAL, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = wrappedVisitor.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("setX")) {
            return new AddSetYMethodVisitor(methodVisitor);
        } else {
            return methodVisitor;
        }
    }
}
