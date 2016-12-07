package com.viewpagerindicator;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the com.wxb.jianbao.adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}
