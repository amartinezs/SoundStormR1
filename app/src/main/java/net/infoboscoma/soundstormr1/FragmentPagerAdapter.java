package net.infoboscoma.soundstormr1;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListView;

import java.util.List;

import Model.Song;

/**
 * Created by Albert on 15/02/2015.
 */
class PagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private MusicRetriever mr;

    public PagerAdapter(FragmentManager fragmentManager, MusicRetriever mr) {
        super(fragmentManager);
        this.mr = mr;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        FirstFragment ff = FirstFragment.newInstance(0, "Page # 0");
        Song[] localSongShowList;
        List<Song> songList = mr.getLocalSongs();
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                ff = FirstFragment.newInstance(0, "Page # 1");
                localSongShowList = new Song[songList.size()];

                for(int i= 0; i < songList.size(); i++){
                    localSongShowList[i] = songList.get(i);
                }
                ff.setDades(localSongShowList);
                return ff;
            case 1: // Fragment # 0 - This will show FirstFragment different title
                ff = FirstFragment.newInstance(2, "Page # 2");

                localSongShowList = new Song[songList.size()];

                for(int i= 0; i < songList.size(); i++){
                    localSongShowList[i] = songList.get(i);
                }
                ff.setDades(localSongShowList);

                return ff;
            case 2: // Fragment # 1 - This will show
                ff = FirstFragment.newInstance(0, "Page # 3");

                ff = FirstFragment.newInstance(0, "Page # 1");
                localSongShowList = new Song[songList.size()];

                for(int i= 0; i < songList.size(); i++){
                    localSongShowList[i] = songList.get(i);
                }

                ff.setDades(localSongShowList);

                return ff;
            default:
                return ff;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "Music";
            case 1:
                return "Play List";
            case 2:
                return "Favorites";
            default:
                return "fail";


        }
    }

}
