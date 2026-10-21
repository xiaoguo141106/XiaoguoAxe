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

package com.xiaoguo.axe.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class ImmortalBladeItem extends AxeItem {
    /**
     * 下界合金模板（INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15, NETHERITE_TOOL_MATERIALS），
     * 只把 durability 换成 8192。
     * <p>
     * 注意：不能靠 Item.Properties#durability(8192) 来改耐久。AxeItem 的构造函数会调用
     * Item.Properties#axe(material, ...) → ToolMaterial#applyToolProperties → applyCommonProperties，
     * 其中会用 material.durability() 把 Properties 上的耐久覆盖掉。所以必须自定义 ToolMaterial。
     */
    public static final ToolMaterial IMMORTAL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            8192,
            9.0F,
            4.0F,
            15,
            ItemTags.NETHERITE_TOOL_MATERIALS
    );

    /**
     * 攻击力参数是「额外加成」而非最终值：
     * 最终伤害 = 1(角色基础) + material.attackDamageBonus(4) + 此参数(20) = 25。
     */
    private static final float ATTACK_DAMAGE_BONUS = 20.0F;

    /**
     * 必须接收外部传入的 Item.Properties，而不是自己 new。
     * MC 26.2 的 Item 构造函数要求 Properties 上已设置 item id，
     * 这个 id 由 NeoForge 的 DeferredRegister.Items#registerItem 注入。
     */
    public ImmortalBladeItem(Item.Properties properties) {
        super(IMMORTAL_MATERIAL, ATTACK_DAMAGE_BONUS, -3.0F, properties.fireResistant()
                // 自带附魔：注册物品时附魔注册表还没就绪（附魔是数据包驱动），
                // 所以必须用 delayedComponent 延迟到数据包加载后再构造。
                .delayedComponent(DataComponents.ENCHANTMENTS, ImmortalBladeItem::createDefaultEnchantments));
    }

    private static ItemEnchantments createDefaultEnchantments(HolderLookup.Provider registries) {
        var enchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        // 火焰附加 III（高于原版满级 II，组件层面允许，灼烧时长按等级生效）
        enchantments.set(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT), 3);
        // 横扫之刃 I（原版只允许剑，但直接写组件不受此限制）
        enchantments.set(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE), 1);
        return enchantments.toImmutable();
    }
}