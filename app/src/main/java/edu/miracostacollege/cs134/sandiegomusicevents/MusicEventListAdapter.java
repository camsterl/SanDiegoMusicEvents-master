package edu.miracostacollege.cs134.sandiegomusicevents;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracostacollege.cs134.sandiegomusicevents.model.MusicEvent;

public class MusicEventListAdapter extends ArrayAdapter<MusicEvent> {

    //declare member variables to store the params
    private Context mContext;
    private int mResourceId;
    private List<MusicEvent> mAllEvents;

    //this constructor is being called  by MainActivity
    public MusicEventListAdapter(@NonNull Context context, int resource, @NonNull List<MusicEvent> objects) {
        super(context, resource, objects);
        mContext = context;
        mResourceId = resource;
        mAllEvents = objects;

    }


    //in order to bridge the view (music_event_list_item) with model (MusicEvent) we override;
    //crtl 0 = override


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // inflate custom layout with data from List<MusicEvents>

        MusicEvent focusedEvent = mAllEvents.get(position);

        // manually inflate custom layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //tell inflater to inflate music_event_list_item

        View customView = inflater.inflate(mResourceId, null);

        //fill parts of custom view

        ImageView listItemImageView = customView.findViewById(R.id.listItemImageView);
        TextView listDateTextView = customView.findViewById(R.id.listItemDateTextView);
        TextView listTitleTextView = customView.findViewById(R.id.listItemTitleTextView);

        //put info in text views and image views
        listTitleTextView.setText(focusedEvent.getArtist());
        listDateTextView.setText(focusedEvent.getDate());

        //load pic
        AssetManager am = mContext.getAssets();

        try
        {
            InputStream stream = am.open(focusedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, "This is an image of " + focusedEvent.getArtist());
            //put image in
            listItemImageView.setImageDrawable(image);
        }
        catch (IOException e) {
            Log.e("SD Music Events", e.getMessage());
        }

        //return customview with all information
        return customView;
    }
}
