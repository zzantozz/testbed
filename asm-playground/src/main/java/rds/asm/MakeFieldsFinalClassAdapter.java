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

    public MakeFieldsFinalClassAdapter(ClassVisitor wrappedVisitor) {
        super(wrappedVisitor);
        this.wrappedVisitor = wrappedVisitor;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return wrappedVisitor.visitField(access | Opcodes.ACC_FINAL, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = wrappedVisitor.visitMethod(access, name, desc, signature, exceptions);
        return new AddSetYMethodVisitor(methodVisitor);
    }
}
