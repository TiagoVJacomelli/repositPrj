package com.tiagojacomelli.bike4life.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.firebase.GetEventsUseCase;
import com.tiagojacomelli.bike4life.implementations.EventListView;
import com.tiagojacomelli.bike4life.implementations.ItemViewHolderListener;
import com.tiagojacomelli.bike4life.models.Event;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements EventListView, ItemViewHolderListener {

    private GetEventsUseCase eventUseCase = new GetEventsUseCase(this);

    private RecyclerView eventListRV;

    Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        eventListRV = findViewById(R.id.mainEventList);
        createEvent = findViewById(R.id.create_button);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreatEvent();
            }
        });

        eventUseCase.getEventList();
    }

    private void openCreatEvent (){
        Intent intent = new Intent(HomeActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setEvents(ArrayList<Event> list) {

        GroupAdapter<ViewHolder> mAdapter = new GroupAdapter<>();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mAdapter.clear();

        eventListRV.setLayoutManager(mLayoutManager);
        eventListRV.setAdapter(mAdapter);

        for ( Event event: list ) {
            ItemViewHolder item = new ItemViewHolder(event, this);
            mAdapter.add(item);
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void eventsFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToItemDetails(String eventId) {
        Intent intent = new Intent(this , EventDetailsDetailsActivity.class);
        intent.putExtra(Event.EVENT_FLAG, eventId);
        startActivity( intent );
    }
}
