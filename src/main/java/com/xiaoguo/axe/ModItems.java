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

import com.xiaoguo.axe.item.ImmortalBladeItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(XiaoguoAxeMod.MODID);

    /**
     * 必须用 registerItem 而不是 register：
     * registerItem 会 new 一个 Item.Properties 并调用 setId(...) 注入物品 id，
     * 再交给工厂函数。MC 26.2 的 Item 构造函数强制要求这个 id 存在。
     * 防火/默认附魔等物品特性统一在 ImmortalBladeItem 构造函数里配置。
     */
    public static final DeferredItem<ImmortalBladeItem> IMMORTAL_BLADE =
            ITEMS.registerItem("immortal_blade", ImmortalBladeItem::new);
}