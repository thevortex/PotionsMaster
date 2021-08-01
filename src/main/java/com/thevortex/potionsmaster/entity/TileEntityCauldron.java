package com.thevortex.potionsmaster.entity;

import net.minecraft.block.BlockState;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.model.data.IModelData;

import static com.thevortex.potionsmaster.init.ModEntity.TILE_CAULDRON;
import static net.minecraft.block.CampfireBlock.LIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thevortex.potionsmaster.blocks.Cauldron;
import com.thevortex.potionsmaster.capabilities.IBrew;


public class TileEntityCauldron extends TileEntity implements IBrew, ITickableTileEntity {

    public List<EffectInstance> INPUT_A;
    public List<EffectInstance> INPUT_B;
    public boolean isBrewing = false;
    public boolean isReadyToBrew = false;
    public boolean isComplete = false;
    public boolean hasCatalyst = false;
    public int brewTime;
    public ServerWorld world;
    private List<EffectInstance> OUTPUT;

    public TileEntityCauldron() {
        super(TILE_CAULDRON);
        this.INPUT_A = new ArrayList<EffectInstance>();
        this.INPUT_B = new ArrayList<EffectInstance>();
        this.OUTPUT = new ArrayList<EffectInstance>();
        this.isBrewing = false;
        this.isReadyToBrew = false;
        this.isComplete = false;
        this.hasCatalyst = false;
        this.brewTime = 0;
    }

    public TileEntityCauldron(World world, BlockState state) {
        super(TILE_CAULDRON);
        this.INPUT_A = new ArrayList<EffectInstance>();
        this.INPUT_B = new ArrayList<EffectInstance>();
        this.OUTPUT = new ArrayList<EffectInstance>();
        this.isBrewing = false;
        this.isReadyToBrew = false;
        this.isComplete = false;
        this.hasCatalyst = false;
        this.brewTime = 0;

    }

    @Override
    public IModelData getModelData() {
        return super.getModelData();
    }

    @Override
    public BlockState getBlockState() {
        return super.getBlockState();
    }


    public List<EffectInstance> getOutput() {
        return this.OUTPUT;
    }

    @Override
    public void giveCatalyst() {
        this.hasCatalyst = true;
        this.setChanged();
    }

    @Override
    public void startBrewing() {
        this.isBrewing = true;
        this.setChanged();
    }

    @Override
    public void goForReady() {
        this.isReadyToBrew = true;
        this.setChanged();
    }

    @Override
    public void brewComplete() {
        this.isComplete = true;
        this.setChanged();
    }

    @Override
    public boolean isBrewing() {
        return this.isBrewing;
    }

    @Override
    public boolean isReadyToBrew() {
        return this.isReadyToBrew;
    }

    @Override
    public boolean isComplete() {
        return this.isComplete;
    }

    @Override
    public boolean hasCatalyst() {

        return this.hasCatalyst;
    }

    @Override
    public void resetAll() {
        this.INPUT_A = new ArrayList<EffectInstance>();
        this.INPUT_B = new ArrayList<EffectInstance>();
        this.OUTPUT = new ArrayList<EffectInstance>();
        ServerWorld world = (ServerWorld) this.getLevel();
        Cauldron thisCauldron = (Cauldron) world.getBlockState(this.getBlockPos()).getBlock();
        thisCauldron.setWaterLevel(this.getLevel(), this.getBlockPos(), this.getBlockState(), 0);
        this.isBrewing = false;
        this.isReadyToBrew = false;
        this.isComplete = false;
        this.hasCatalyst = false;
        this.brewTime = 0;
    }

    @Override
    public int getBrewTime() {
        return this.brewTime;
    }

    @Override
    public void tick() {
        if (!this.getLevel().isClientSide()) {
            ServerWorld worldIn = (ServerWorld) this.getLevel();
            if (worldIn.getBlockState(this.getBlockPos().below()).getValue(LIT)) {
                TileEntityCauldron cauldron = (TileEntityCauldron) worldIn.getBlockEntity(this.getBlockPos());

                if (cauldron.isBrewing()) {
                    cauldron.brewTime++;
                }
                if (cauldron.brewTime == 1200) {
                    cauldron.brewTime = 0;
                    cauldron.isComplete = true;
                    cauldron.isBrewing = false;
                    cauldron.hasCatalyst = false;
                    cauldron.isReadyToBrew = false;


                    for (EffectInstance a : cauldron.INPUT_A) {
                        EffectInstance e;
                        if (cauldron.INPUT_B.contains(a)) {
                            e = new EffectInstance(a.getEffect().getEffect(), a.getDuration() + a.getDuration(), a.getAmplifier(), false, false);
                            cauldron.INPUT_B.remove(a);
                        } else {
                            e = new EffectInstance(a.getEffect().getEffect(), a.getDuration(), a.getAmplifier(), false, false);
                        }
                        this.OUTPUT.add(e);


                    }
                    for (EffectInstance b : cauldron.INPUT_B) {
                        EffectInstance f = new EffectInstance(b.getEffect().getEffect(), b.getDuration(), b.getAmplifier(), false, false);
                        this.OUTPUT.add(f);

                    }
                    Cauldron cauldronBlock = (Cauldron) cauldron.getBlockState().getBlock();
                    cauldronBlock.setWaterLevel(worldIn, this.getBlockPos(), cauldronBlock.defaultBlockState(), 1);
                }
            }
        }
        this.setChanged();
    }


}
