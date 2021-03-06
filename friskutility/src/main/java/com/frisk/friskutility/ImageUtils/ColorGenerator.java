package com.frisk.friskutility.ImageUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;



/**
 * Created by Faizan for Netree  on 18,January,2019
 * Version foop 2.0
 * Revision foop 2.0
 */

public class ColorGenerator {

//    public static final ColorGenerator DEFAULT;

    public static final ColorGenerator MATERIAL;

    static {
        MATERIAL = create(Arrays.asList(
                0xffe57373,
                0xfff06292,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffaed581,
                0xffff8a65,
                0xffd4e157,
                0xffffd54f,
                0xffffb74d,
                0xffa1887f,
                0xff90a4ae
        ));

    }

    private final List<Integer> mColors;
    private final Random mRandom;

    private static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }
}