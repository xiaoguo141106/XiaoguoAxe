/*
 * Copyright (c) 2026 Xiaoguo. All rights reserved.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package com.xiaoguo.axe.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class ImmortalBladeItem extends AxeItem {
    // 构造函数现在接收 Item.Properties 参数
    public ImmortalBladeItem(Item.Properties properties) {
        super(ToolMaterial.NETHERITE, 14.0F, -3.0F, properties
                .durability(8192)
                .fireResistant()
        );
    }
}