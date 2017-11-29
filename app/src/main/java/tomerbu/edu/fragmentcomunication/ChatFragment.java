package tomerbu.edu.fragmentcomunication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        RecyclerView rvChat = v.findViewById(R.id.rvChat);

        //TODO: Add the adapter.

        rvChat.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    public void onTextChanged(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


    //adapter RecyclerView.Adapater
    //before the adapter - > ViewHolder

    static class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
        private Context context;
        private LayoutInflater inflater;
        private ArrayList<String> chatData = new ArrayList<>();

        public ChatAdapter(Context context) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //problem we have an xml (not a view) -> inflate the xml to a view.
            View v = inflater.inflate(R.layout.chat_item, parent, false);
            return new ChatViewHolder(v);
        }
        @Override
        public void onBindViewHolder(ChatViewHolder h, int position) {
            //UI: Data Binding
            //update the chatView in the current position
            String chatItem = chatData.get(position);
            h.tvChatItem.setText(chatItem);
        }
        @Override
        public int getItemCount() {
            return chatData.size();
        }
        //purpose/Job definition:
        //View Holder - > findViewByID for the chat_item.xml:
        static class ChatViewHolder extends RecyclerView.ViewHolder {
            //fields
            TextView tvChatItem;

            public ChatViewHolder(View v) {
                super(v);
                tvChatItem = v.findViewById(R.id.tvChatItem);
            }
        }
    }
}
