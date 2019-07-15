package net.ggtylerr.pingpong.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.arguments.*;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

import java.util.Collection;
import java.util.Iterator;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * Commands:
 * /pingpong
 * /pingpong [name]
 *
 * Expected output:
 * No [name]: Displays ping of current player in ms.
 * [name]: Displays ping of selected user in ms.
 */

public class Ping {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("pingpong")
            .executes(context -> execute(context.getSource()))
            .then(CommandManager.argument("targets", EntityArgumentType.players())
                .executes(context -> execute(context.getSource(), EntityArgumentType.getPlayers(context, "targets")))));
    }
    public static int execute(ServerCommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayer();
        int ping = player.pingMilliseconds;
        source.sendFeedback(new TranslatableText("chat.type.admin",new Object[]{player.getDisplayName(), ping + " ms"}),false);
        return 1;
    }
    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            ServerPlayerEntity player = (ServerPlayerEntity)iterator.next();
            int ping = player.pingMilliseconds;
            source.sendFeedback(new TranslatableText("chat.type.admin",new Object[]{player.getDisplayName(), ping + " ms"}),false);
        }
        return 1;
    }
}