package com.udasboot.bootcore;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod("dascore")
@Mod.EventBusSubscriber(modid = BootCore.MOD_ID, bus = Bus.MOD)
public class BootCore
{
	
	public static final String MOD_ID = "dascore";
	
    public BootCore() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
