package tv.mapper.roadstuff.world;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import tv.mapper.roadstuff.RoadStuff;
import tv.mapper.roadstuff.config.RSConfig;

import java.util.List;

public class RSPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, RoadStuff.MODID);

    public static final RegistryObject<PlacedFeature> BITUMEN_ORE_PLACED = PLACED_FEATURES.register("bitumen",
            () -> new PlacedFeature(RSConfiguredFeatures.BITUMEN_ORE.getHolder().get(),
                    commonOrePlacement(RSConfig.BITUMEN_CHANCE.get(),
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(RSConfig.BITUMEN_MIN_HEIGHT.get()), VerticalAnchor.absolute(RSConfig.BITUMEN_MAX_HEIGHT.get())))));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
