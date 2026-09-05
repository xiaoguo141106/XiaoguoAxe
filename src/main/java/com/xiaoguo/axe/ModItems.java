/*
 * Copyright (c) 2026 Xiaoguo. All rights reserved.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package com.xiaoguo.axe;

import com.xiaoguo.axe.item.ImmortalBladeItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(XiaoguoAxeMod.MODID);

    public static final DeferredItem<ImmortalBladeItem> IMMORTAL_BLADE =
            ITEMS.registerItem("immortal_blade", properties ->
                    new ImmortalBladeItem(properties)
            );
}