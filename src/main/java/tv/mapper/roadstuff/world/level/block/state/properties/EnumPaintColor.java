package tv.mapper.roadstuff.world.level.block.state.properties;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public enum EnumPaintColor implements StringRepresentable
{
    WHITE(0, "white"),
    YELLOW(1, "yellow");

    private final int id;
    private final String name;

    private EnumPaintColor(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getSerializedName()
    {
        return this.name;
    }

    public String getNameTranslated()
    {
        return Component.translatable("roadstuff.message.paint.color." + this.name).getString();
    }

    public int getId()
    {
        return this.id;
    }

    public static EnumPaintColor getColorByID(int id)
    {
        for(EnumPaintColor color : values())
        {
            if(color.getId() == id)
                return color;
        }
        return null;
    }
}