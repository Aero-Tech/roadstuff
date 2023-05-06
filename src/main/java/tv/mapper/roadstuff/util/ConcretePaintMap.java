package tv.mapper.roadstuff.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tv.mapper.roadstuff.world.level.block.RSBlockRegistry;

import java.util.Objects;

public class ConcretePaintMap extends Int2ObjectArrayMap<Block>
{
    private static final long serialVersionUID = -2560468416060236377L;

    public ConcretePaintMap(boolean slope)
    {
        super();
        if(!slope)
            register(0, 0, RSBlockRegistry.CONCRETE.get());
        else if(slope)
            register(0, 0, RSBlockRegistry.CONCRETE_SLOPE.get());

        int index = 1;

        Block block;

        for(RegistryObject<Block> object : RSBlockRegistry.MOD_PAINTABLEBLOCKS)
        {
            block = object.get();
            String blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();

            if(blockPath.contains("concrete_"))
            {
                if(blockPath.contains("_white_"))
                {
                    if(blockPath.contains("slope") && slope)
                    {
                        register(0, index, block);
                        index++;
                    }
                    else if(!blockPath.contains("slope") && !slope)
                    {
                        register(0, index, block);
                        index++;
                    }
                }

            }
        }

        if(!slope)
            register(1, 0, RSBlockRegistry.CONCRETE.get());
        else if(slope)
            register(1, 0, RSBlockRegistry.CONCRETE_SLOPE.get());

        index = 1;
        for(RegistryObject<Block> object : RSBlockRegistry.MOD_PAINTABLEBLOCKS)
        {
            block = object.get();
            String blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();

            if(blockPath.contains("concrete_"))
            {
                if(blockPath.contains("_yellow_"))
                {
                    if(blockPath.contains("slope") && slope)
                    {
                        register(1, index, block);
                        index++;
                    }
                    else if(!blockPath.contains("slope") && !slope)
                    {
                        register(1, index, block);
                        index++;
                    }
                }
            }
        }
    }

    public void register(int color, int pattern, Block block)
    {
        int id = 0;
        if(color == 1)
            id += ModConstants.PATTERNS;
        this.put(id + pattern, block);
    }

    public Block getBlockFor(int color, int pattern)
    {
        int id = 0;
        if(color == 1)
            id += ModConstants.PATTERNS;
        return this.get(id + pattern);
    }

    public int[] getParamsFor(Block blockIn)
    {
        int i = 0;
        int color = 0, pattern = 0;
        Block block;
        while(i < this.size())
        {
            block = this.get(i);
            if(block == blockIn)
            {
                if(i >= ModConstants.PATTERNS)
                {
                    color = 1;
                    pattern = i - ModConstants.PATTERNS;
                }
                else
                    pattern = i;
            }
            i++;
        }
        return new int[] {color, pattern};
    }
}