package net.ggtylerr.pingpong.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

import java.util.Collection;
import java.util.Iterator;

/**
 * Commands:
 * /pingpong
 * /pingpong [name]
 *
 * Expected output:
 * No [name]: Displays ping of current player in ms.
 * [name]: Displays ping of selected user in ms.
 */

/**
 * NOTHING WORKS
 *
 * EVERYTHING IS SHIT
 *
 * FUCK THIS
 *
 * AUGHHHHHHHH
 *
 * I"VE BEEN ON THIS STUPID MOD
 *
 * FOR 10 HOURS
 *
 * AUGHHHHHHHHHHHHHHHHHHHHHHHHHHH
 */

public class Ping implements ClientCommandPlugin {
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal("pingpong")
            .executes(context -> execute(context.getSource())));
            //.then(ArgumentBuilders.argument("targets", EntityArgumentType.players())
            //    .executes(context -> execute(context.getSource(), EntityArgumentType.getPlayers(context,"targets")))));
    }
    public static int execute(CottonClientCommandSource source) throws CommandSyntaxException {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayNetworkHandler handler = mc.player.networkHandler;
        Collection<PlayerListEntry> players = handler.getPlayerList();
        int maxPlayers = players.size();
        for (int i = 0; i < maxPlayers; i++) {
            Object[] playerArr = players.toArray();
            Object player = playerArr[i];
            source.sendFeedback(new TranslatableText("chat.type.admin",players));
        }
        // source.sendFeedback(new TranslatableText("chat.type.admin",new Object[]{player.getDisplayName(), ping + " ms"}));
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