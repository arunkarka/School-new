package com.ringpi.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HMactivity extends AppCompatActivity {
  private RecyclerView mFirestoreList;
  private FirebaseFirestore firebaseFirestore;
  private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_mactivity);

        mFirestoreList=findViewById(R.id.firestore_list);
        firebaseFirestore=FirebaseFirestore.getInstance();

        Query query=firebaseFirestore.collection("school-profiles/zphs-indira-nagar/session-list");
        FirestoreRecyclerOptions<Sessions> options=new FirestoreRecyclerOptions.Builder<Sessions>()
                .setQuery(query, Sessions.class)
                .build();


        adapter=new FirestoreRecyclerAdapter<Sessions,SessionsViewHolder>(options) {

            @NonNull
            @Override
            public SessionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);
                return new SessionsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull SessionsViewHolder holder, int position, @NonNull Sessions model) {
             holder.faculty_name.setText(model.getCurrentTeacher());
                holder.class_details.setText(model.getClassname());
              //  holder.subject.setText(model.getSubject());
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new GridLayoutManager(this, 2));
        mFirestoreList.setAdapter(adapter);


    }

    private class SessionsViewHolder extends RecyclerView.ViewHolder {
        private TextView faculty_name;
        private TextView class_details;
        private TextView subject;

        public SessionsViewHolder(@NonNull View itemView) {
            super(itemView);
            faculty_name=itemView.findViewById(R.id.faculty_name);
           class_details=itemView.findViewById(R.id.class_name);
            //subject=itemView.findViewById(R.id.subjectdetails);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}