package com.tiagojacomelli.bike4life.activities;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.implementations.ItemViewHolderListener;
import com.tiagojacomelli.bike4life.models.Event;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class ItemViewHolder extends Item {

    private Event event;
    private ItemViewHolderListener viewHolderListener;
    private boolean isSwitchOn = false;

    public ItemViewHolder(Event event, ItemViewHolderListener viewHolderListener) {
        this.event = event;
        this.viewHolderListener = viewHolderListener;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {

        String eventName = event.getEventName();
        String eventDate = "Data: " + event.getEventDate();
        String eventLimit = "Limite de pessoa: " + event.getMaxPeople();

        LinearLayout cardContainer = viewHolder.itemView.findViewById(R.id.cardContainer);
        TextView cardHeader = viewHolder.itemView.findViewById(R.id.card_header);
        TextView cardDate = viewHolder.itemView.findViewById(R.id.card_data);
        TextView cardLimit = viewHolder.itemView.findViewById(R.id.card_limit);

        cardHeader.setText( eventName );
        cardDate.setText( eventDate );
        cardLimit.setText( eventLimit );

        cardContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolderListener.goToItemDetails(event.getEventId());
            }
        });

//        if (isSwitchOn && !event.isMyEvent()) {
//            cardContainer.setVisibility(View.VISIBLE);
//        }
    }

    public void setSwitchOn(boolean switchOn) {
        isSwitchOn = switchOn;
    }

    @Override
    public int getLayout() {
        return R.layout.event_card_layout;
    }
}
