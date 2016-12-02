package fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 赵自强 on 2016/12/1.
 */

public class jianbao_Adapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public jianbao_Adapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public void setList(List list){

        this.list=list;

    }
}
