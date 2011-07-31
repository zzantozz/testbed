package rds.asm;

import org.objectweb.asm.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/30/11
 * Time: 8:47 AM
 */
public class AddSetYMethodVisitor extends MethodAdapter {
    private MethodVisitor wrappedVisitor;

    public AddSetYMethodVisitor(MethodVisitor methodVisitor) {
        super(methodVisitor);
        wrappedVisitor = methodVisitor;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        wrappedVisitor.visitFieldInsn(opcode, owner, name, desc);
        if (opcode == Opcodes.PUTFIELD
                    && owner.equals(Type.getInternalName(TestPojo.class))
                && name.equals("x")
                && desc.equals(Type.getDescriptor(Integer.TYPE))) {
            Label label = new Label();
            wrappedVisitor.visitLabel(label);
            wrappedVisitor.visitLineNumber(24, label);
            wrappedVisitor.visitVarInsn(Opcodes.ALOAD, 0);
//            wrappedVisitor.visitIntInsn(Opcodes.BIPUSH, 10); // This makes y be set to 10
            wrappedVisitor.visitVarInsn(Opcodes.ILOAD, 1); // This makes y be set to the argument of the setX() method
            wrappedVisitor.visitFieldInsn(Opcodes.PUTFIELD, owner, "y", Type.getDescriptor(Integer.TYPE));
        }
    }
/* bytecode to assign 10 to y in setX()
   L2
    LINENUMBER 24 L2
    ALOAD 0
    BIPUSH 10
    PUTFIELD rds/asm/TestPojo.y : I
*/

}
