package net.infoboscoma.soundstormr1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import Model.Song;

/**
 * Created by Albert on 15/02/2015.
 */
class SongsAdapter extends ArrayAdapter<Song> {
    private Song[] dades;

    SongsAdapter(Activity context, Song[] dades) {
        super(context, R.layout.listitem_songs, dades);
        this.dades = dades;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;
        Vista vista;

        if (element == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            element = inflater.inflate(R.layout.listitem_songs, null);

            vista = new Vista();
            vista.nom = (TextView) element.findViewById(R.id.tv_songName);
            vista.autor = (TextView) element.findViewById(R.id.tv_autor);
            vista.album = (TextView) element.findViewById(R.id.tv_album);

            element.setTag(vista);
        } else {
            vista = (Vista) element.getTag();
        }

        vista.nom.setText(dades[position].getTitle());
        vista.autor.setText(dades[position].getArtist());
        vista.album.setText(dades[position].getAlbum());

        return element;
    }


    private class Vista {
        public TextView nom;
        public TextView autor;
        public TextView album;
    }
}