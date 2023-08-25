package com.thevortex.potionsmaster.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Mortar extends Block {

    public VoxelShape blockshape = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 5.0D, 13.0D);


    public Mortar() {

        super(Properties.of().sound(SoundType.STONE).strength(2.0f));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        // TODO Auto-generated method stub
        return blockshape;
    }

}
