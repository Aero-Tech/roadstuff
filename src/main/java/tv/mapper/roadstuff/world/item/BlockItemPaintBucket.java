package tv.mapper.roadstuff.world.item;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import tv.mapper.roadstuff.world.level.block.state.properties.EnumPaintColor;

public class BlockItemPaintBucket extends BlockItem
{
    public BlockItemPaintBucket(Block blockIn, Properties builder)
    {
        super(blockIn, builder);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level player, List<Component> list, TooltipFlag flag)
    {
        super.appendHoverText(stack, player, list, flag);
        if(stack.hasTag())
        {
            String color = EnumPaintColor.getColorByID(stack.getTag().getInt("color")).getNameTranslated();

            int paint = (stack.getTag().getInt("paint") * 100) / 8;
            if(paint < 10)
                color = "X";
            list.add(Component.literal(Component.translatable("roadstuff.message.brush.gui.color").getString() + color));
            list.add(Component.literal(Component.translatable("roadstuff.message.brush.gui.paint").getString() + paint + "%"));
        }
    }
}