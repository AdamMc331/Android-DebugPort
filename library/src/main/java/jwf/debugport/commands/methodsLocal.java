package jwf.debugport.commands;

import bsh.CallStack;
import bsh.Interpreter;
import jwf.debugport.annotations.Command;
import jwf.debugport.commands.descriptors.MethodDescriptor;

/**
 *
 */
@Command
public class methodsLocal {
    @Command.Help("Show all of the locally-declared methods for the provided object.")
    public static void invoke(Interpreter interpreter, CallStack callStack, Object obj) {
        invoke(interpreter, callStack, obj.getClass());
    }

    @Command.Help("Show all of the locally-declared methods for the provided class.")
    public static void invoke(Interpreter interpreter, CallStack callStack, Class klass) {
        if (klass == null) {
            interpreter.println("value is null");
            return;
        }
        MethodDescriptor[] methods = MethodDescriptor.fromMethods(klass.getDeclaredMethods());
        StringBuilder sb = new StringBuilder();

        sb.append("declared methods: {\n");
        for (MethodDescriptor method : methods) {
            sb.append(CommandUtils.indent(1));
            sb.append(method.toString());
            sb.append("\n");
        }
        sb.append("}");

        interpreter.println(sb.toString());
    }
}
