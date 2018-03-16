import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vijayraj bafna on 16-Mar-18.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    int tot;
    public PagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
