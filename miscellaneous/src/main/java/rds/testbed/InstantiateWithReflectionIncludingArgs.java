package rds.testbed;

    import java.lang.reflect.Constructor;
    import java.util.*;

    public class InstantiateWithReflectionIncludingArgs {
        public static void main(String[] args) throws Exception {
            String className = args[0];
            List<Object> argList = new ArrayList<Object>();
            if (args.length > 1) {
                argList.addAll(Arrays.asList(args).subList(1, args.length));
            }
            Class c = Class.forName(className);
            List<Class<?>> argTypes = new ArrayList<Class<?>>();
            for (Object arg : argList) {
                argTypes.add(arg.getClass());
            }
            Constructor constructor = c.getConstructor(argTypes.toArray(new Class<?>[argTypes.size()]));
            Object o = constructor.newInstance(argList.toArray(new Object[argList.size()]));
            System.out.println("Created a " + o.getClass() + ": " + o);
        }
    }
