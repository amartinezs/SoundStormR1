package net.infoboscoma.soundstormr1;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import Model.Song;

/**
 * Created by Albert on 15/02/2015.
 */
public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private ListView llistaSongs;
    private Song[] dades;
    private MusicPlayer mp;
    private boolean playing = false;


    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }







    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container,
                false);
        final Activity activity = getActivity();
        llistaSongs = (ListView) rootView.findViewById(R.id.list);

        final SongsAdapter adapter = new SongsAdapter(activity,dades);
        llistaSongs.setAdapter(adapter);

        llistaSongs.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position < dades.length && playing == false){
                    playing = true;
                    mp = new MusicPlayer(dades[position]);
                    mp.startMusic(activity.getBaseContext());
                } else if (playing) {
                    mp.stopMusic();
                    playing = false;

                }
            }
        });

        llistaSongs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                openAlert(view,dades[position]);

                return true;
            }
        });

        return rootView;
    }

    private void openAlert(View view, final Song song) {
        final Activity activity = getActivity();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        //alertDialogBuilder.setTitle(this.getTitle()+ " decision");
        alertDialogBuilder.setMessage("Fes clic per editar info");
        // set positive button: Yes message
       /* alertDialogBuilder.setPositiveButton("Eliminar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // go to a new activity of the app
            }
        });*/
        // set negative button:
       /* alertDialogBuilder.setNegativeButton("Favorits",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // cancel the alert box and put a Toast to the user
                dialog.cancel();
                Toast.makeText(getActivity().getBaseContext(), "Modifiques la info de la canço",Toast.LENGTH_LONG).show();
            }
        });*/
        // set neutral button:
        alertDialogBuilder.setNeutralButton("Editar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // exit the app and go to the HOME
                Intent editActivity = new Intent(activity.getBaseContext(),EditActivity.class);
                System.out.println(song.getTitle());
                Bundle b = new Bundle();
                b.putSerializable("song",song);
                editActivity.putExtras(b);

                startActivity(editActivity);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
}


    public Song[] getDades() {
        return dades;
    }

    public void setDades(Song[] dades) {
        this.dades = dades;
    }
}