package com.usarral.killbees.client.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

import static com.usarral.killbees.client.KillbeesClient.KILL_BEES_PACKET_ID;

public class KillbeesClientCommand {
  public static int run(CommandContext<FabricClientCommandSource> commandContext) {
    PacketByteBuf buf = PacketByteBufs.create();
    ClientPlayNetworking.send(KILL_BEES_PACKET_ID, buf);






    return 0;
  }
}
