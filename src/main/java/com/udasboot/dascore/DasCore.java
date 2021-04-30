package com.udasboot.dascore;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod("dascore")
@Mod.EventBusSubscriber(modid = DasCore.MOD_ID, bus = Bus.MOD)
public class DasCore
{
	
	public static final String MOD_ID = "dascore";
	
    public DasCore() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
}
