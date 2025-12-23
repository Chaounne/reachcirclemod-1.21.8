package me.chaounne.reachcirclemod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReachCircleMod implements ModInitializer {
	public static final String MOD_ID = "reachcirclemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("ReachCircleMod initialized !");
	}
}