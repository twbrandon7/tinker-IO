package tinker_io.api.vanilla;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class PosInfo {
	public static List<EnumFacing> getFacingList() {
		List<EnumFacing> facings = Arrays.asList(EnumFacing.VALUES);
		return facings;
	}
	
	public static List<BlockPos> getAllAmountBlockPosList(BlockPos pos) {
		List<BlockPos> posList = PosInfo.getFacingList().stream()
				.map(pos::offset)
				.collect(Collectors.toList());
		return posList;
	}
}
