package farn.itemNbt.mixin;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.util.Annotations;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("RedundantMethodOverride")
public class MixinPlugin implements IMixinConfigPlugin {

    public void onLoad(String mixinPackage) {
    }

    public String getRefMapperConfig() {
        return null;
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    public List<String> getMixins() {
        return null;
    }

    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        for (ListIterator<MethodNode> it = targetClass.methods.listIterator(); it.hasNext(); ) {
            MethodNode method = it.next();
            if (Annotations.getVisible(method, ShadowSuperConstructor.class) != null) {
                it.remove();
                transformCalls(targetClass, call -> {
                    if (call.name.equals(method.name) && call.desc.equals(method.desc)) {
                        call.setOpcode(Opcodes.INVOKESPECIAL);
                        call.name = "<init>";
                        call.owner = targetClass.superName;
                    }
                });
                continue;
            }

            if (Annotations.getVisible(method, NewConstructor.class) != null) {
                transformCalls(targetClass, call -> {
                    if (call.name.equals(method.name) && call.desc.equals(method.desc)) {
                        call.setOpcode(Opcodes.INVOKESPECIAL);
                        call.name = "<init>";
                    }
                });
                method.name = "<init>";
            }


        }
    }

    private static void transformCalls(ClassNode classNode, Consumer<MethodInsnNode> consumer) {
        for (MethodNode method : classNode.methods) {
            for (AbstractInsnNode insn : method.instructions) {
                if (insn instanceof MethodInsnNode) {
                    MethodInsnNode call = (MethodInsnNode) insn;
                    consumer.accept(call);
                }
            }
        }
    }

}
