package org.wcc;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.wcc.data.TreeLikePath;

import java.util.Arrays;

public class TreeLikePathTest {

    /**
     * test data is a sub path that looks like this:
     * 1 -- + -- 2 -- 3
     *      + -- 4 -- + -- 5
     *                + -- 6
     */
    @Test
    public void should_ReturnTrue_When_CompareSubPathWithListOfIntegers() {
        // given
        TreeLikePath<Integer> treeLikePath6 = new TreeLikePath<>(6, null);
        TreeLikePath<Integer> treeLikePath5 = new TreeLikePath<>(5, null);
        TreeLikePath<Integer> treeLikePath4 = new TreeLikePath<>(4, Arrays.asList(treeLikePath5, treeLikePath6));
        TreeLikePath<Integer> treeLikePath3 = new TreeLikePath<>(3, null);
        TreeLikePath<Integer> treeLikePath2 = new TreeLikePath<>(2, Arrays.asList(treeLikePath3, treeLikePath4));
        TreeLikePath<Integer> treeLikePath1 = new TreeLikePath<>(1, Arrays.asList(treeLikePath2));

        // when

        // then
        Assert.assertTrue(treeLikePath1.includes(Arrays.asList(1, 2, 3)));
        Assert.assertTrue(treeLikePath1.includes(Arrays.asList(1, 2, 4, 5)));
        Assert.assertTrue(treeLikePath1.includes(Arrays.asList(1, 2, 4, 6)));

        Assert.assertFalse(treeLikePath1.includes(Arrays.asList(1)));
        Assert.assertFalse(treeLikePath1.includes(Arrays.asList(1, 2, 4, 5, 6)));
        Assert.assertFalse(treeLikePath1.includes(Arrays.asList(0, 1, 2, 4, 5)));
        Assert.assertFalse(treeLikePath1.includes(Arrays.asList(2, 3)));
    }

    @Test
    public void should_Return_Paths_Count_When_Asked() {
        // given
        TreeLikePath<Integer> treeLikePath6 = new TreeLikePath<>(6, null);
        TreeLikePath<Integer> treeLikePath5 = new TreeLikePath<>(5, null);
        TreeLikePath<Integer> treeLikePath4 = new TreeLikePath<>(4, Arrays.asList(treeLikePath5, treeLikePath6));
        TreeLikePath<Integer> treeLikePath3 = new TreeLikePath<>(3, null);
        TreeLikePath<Integer> treeLikePath2 = new TreeLikePath<>(2, Arrays.asList(treeLikePath3, treeLikePath4));
        TreeLikePath<Integer> treeLikePath1 = new TreeLikePath<>(1, Arrays.asList(treeLikePath2));

        // when
        int size = treeLikePath1.size();

        // then
        Assert.assertThat(size, CoreMatchers.equalTo(3));
    }

}
