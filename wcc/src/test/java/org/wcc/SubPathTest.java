package org.wcc;


import org.junit.Assert;
import org.junit.Test;
import org.wcc.algorithm.Paths;

import java.util.Arrays;

public class SubPathTest {

    /**
     * test data is a sub path that looks like this:
     * 1 -- + -- 2 -- 3
     *      + -- 4 -- + -- 5
     *                + -- 6
     */
    @Test
    public void should_ReturnTrue_When_CompareSubPathWithListOfIntegers() {
        // given
        Paths.SubPath subPath6 = new Paths.SubPath(6, null);
        Paths.SubPath subPath5 = new Paths.SubPath(5, null);
        Paths.SubPath subPath4 = new Paths.SubPath(4, Arrays.asList(subPath5, subPath6));
        Paths.SubPath subPath3 = new Paths.SubPath(3, null);
        Paths.SubPath subPath2 = new Paths.SubPath(2, Arrays.asList(subPath3, subPath4));
        Paths.SubPath subPath1 = new Paths.SubPath(1, Arrays.asList(subPath2));

        // when

        // then
        Assert.assertTrue(subPath1.includes(Arrays.asList(1, 2, 3)));
        Assert.assertTrue(subPath1.includes(Arrays.asList(1, 2, 4, 5)));
        Assert.assertTrue(subPath1.includes(Arrays.asList(1, 2, 4, 6)));

        Assert.assertFalse(subPath1.includes(Arrays.asList(1)));
        Assert.assertFalse(subPath1.includes(Arrays.asList(1, 2, 4, 5, 6)));
    }

}
