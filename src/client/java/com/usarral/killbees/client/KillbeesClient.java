package com.usarral.killbees.client;

import com.usarral.killbees.client.commands.KillbeesClientCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KillbeesClient implements ClientModInitializer {
  public static final String MOD_ID = "killbees";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static final Identifier KILL_BEES_PACKET_ID = new Identifier("killbees", "kill_bees");


  @Override
  public void onInitializeClient() {
    LOGGER.info("Registering {} components", MOD_ID);

    // Register the client command
    ClientCommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess) -> commandDispatcher.register(ClientCommandManager.literal("killbees").executes(KillbeesClientCommand::run)));
  }
}
