/*
 * Xiaoguo's Axe - A powerful ancient battle-axe for Minecraft 26.2 NeoForge.
 * Copyright (C) 2026 Xiaoguo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.xiaoguo.axe;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(XiaoguoAxeMod.MODID)
public class XiaoguoAxeMod {
    public static final String MODID = "xiaoguosaxe";

    public static final Logger LOGGER = LogUtils.getLogger();

    public XiaoguoAxeMod(IEventBus modEventBus) {
        // 关键：把 DeferredRegister 挂到「模组事件总线」上，否则里面的物品永远不会被注册进游戏
        ModItems.ITEMS.register(modEventBus);

        // BuildCreativeModeTabContentsEvent 属于 IModBusEvent，也必须注册到模组总线
        modEventBus.addListener(this::addCreativeTabContents);

        LOGGER.info("XiaoguoAxe loaded, modid = {}", MODID);
    }

    private void addCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.IMMORTAL_BLADE);
        }
    }
}