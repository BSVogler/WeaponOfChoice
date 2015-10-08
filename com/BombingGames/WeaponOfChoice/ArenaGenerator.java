package com.BombingGames.WeaponOfChoice;

import com.bombinggames.wurfelengine.core.Map.Generator;
import java.util.Random;

/**
 *
 * @author Benedikt Vogler
 */
public class ArenaGenerator implements Generator {

	private double seed = 0;

	@Override
	public byte generate(int x, int y, int z) {
		if (seed == 0) {
			seed = Math.random();
		}

		if (z == 0) {
			return 8;
		} else {
			if (z == 1 && getRandom(x, y, z) < 0.05f) //ever twenties block is a pillar 
			{
				return 2;
			}
			if (z == 2 && generate(x, y, z - 1) == 2) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * Returns a random number for each field using the seed.
	 *
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private float getRandom(int x, int y, int z) {
		//generate hash
		int field = x * y * z;//fastes way to generate id for every coodinate
		//bet

		Random generator = new Random((long) seed);
		float output = 0;
		for (int i = 0; i < field; i++) {
			output = generator.nextFloat();
		}

		return output;

	}

	@Override
	public void spawnEntities(int x, int y, int z) {
	}

	@Override
	public void configureLogicBlocks(int x, int y, int z) {
	}

}
