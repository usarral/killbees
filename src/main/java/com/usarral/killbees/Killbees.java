package com.usarral.killbees;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Killbees implements ModInitializer {
  public static final Identifier KILL_BEES_PACKET_ID = new Identifier("killbees", "kill_bees");
  public static final String MOD_ID = "killbees";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static void register() {

    ServerPlayNetworking.registerGlobalReceiver(KILL_BEES_PACKET_ID, (server, player, handler, buf, sender) -> server.execute(() -> {
      ServerWorld world = player.getServerWorld();

      // Leer el número de abejas basandose en la posición del jugador
        int radius = 50;
        List<BeeEntity> bees = world.getEntitiesByClass(BeeEntity.class,
            player.getBoundingBox().expand(radius), bee -> true);

        for (BeeEntity bee : bees) {
          LOGGER.info("Bee at ({}, {}, {})", bee.getX(), bee.getY(), bee.getZ());
            bee.kill();
        }

      LOGGER.info("Killed {} bees reported by {}", bees.size(), player.getName().getString());

        //Random effect to player when killing bees
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 2));
        //Give player a dirt block when killing bees
        player.giveItemStack(new ItemStack(Items.DIRT, 1));

        //Player say on chat that he killed the bees
      player.sendMessage(Text.of("He matado " + bees.size() + " abejas"), false);
    }));
  }

  @Override
  public void onInitialize() {
    register();
  }
}
