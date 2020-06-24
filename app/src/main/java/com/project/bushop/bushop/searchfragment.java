package com.project.bushop.bushop;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class searchfragment extends Fragment {


    private static final String FIRE_LOG="FIRE LOG";
    public FirebaseFirestore db;
    private EditText t2,t3,t4;
    private Button b1;
    private TextView t1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentsearch,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);

        final ArrayList<Person> people=new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        b1=(Button)view.findViewById(R.id.button);
        //t1=(TextView)view.findViewById(R.id.textView5);
        t2=(EditText)view.findViewById(R.id.textView2);
        t3=(EditText)view.findViewById(R.id.textView3);
        t4=(EditText)view.findViewById(R.id.textView);
        final ListView mListView= view.findViewById(R.id.busList);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s2,s3,s4;
                s2="";
                s4="";
                s3="";
                s2=t2.getText().toString();//source
                s2=s2.replace(" ","");
                s3=t3.getText().toString(); //destination
                s3=s3.replace(" ","");
                s4=t4.getText().toString(); //bus_no




                if( !s4.isEmpty() && s2.isEmpty() && s3.isEmpty()) {//for only bus_no entry

                    db.collection("BusInfo").whereEqualTo("bus_no", s4 + "").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            people.clear();
                            PersonListAdapter   madapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                            mListView.setAdapter(madapter);
                            if (task.isSuccessful() && task.isComplete()) {
                                StringBuilder sb = new StringBuilder("");
                                for (QueryDocumentSnapshot document : task.getResult()) {


                                    Person d1=new Person(document.get("bus_no"),document.get("source"),document.get("destination"),document.get("time"));
                                    people.add(d1);
                                }

                            } else {
                                Toast.makeText(getContext(), "error getting doc!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }


                //for only source entry

                else if(!s2.isEmpty() && s4.isEmpty() && s3.isEmpty()) {
                    db.collection("BusInfo").whereEqualTo("source", s2 + "").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            people.clear();
                            PersonListAdapter   madapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                            mListView.setAdapter(madapter);
                            if (task.isSuccessful()) {
                                StringBuilder sb = new StringBuilder("");
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Person d1=new Person(document.get("bus_no"),document.get("source"),document.get("destination"),document.get("time"));
                                    people.add(d1);
                                }


                            } else {
                                Toast.makeText(getContext(), "error getting doc!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

                //for only destination entry


                else if(!s3.isEmpty() && s2.isEmpty() && s4.isEmpty()){
                    db.collection("BusInfo").whereEqualTo("destination", s3 + "").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            people.clear();
                            PersonListAdapter   madapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                            mListView.setAdapter(madapter);
                            if (task.isSuccessful()) {
                                StringBuilder sb = new StringBuilder("");
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Person d1=new Person(document.get("bus_no"),document.get("source"),document.get("destination"),document.get("time"));
                                    people.add(d1);

                                }

                            } else {
                                Toast.makeText(getContext(), "error getting doc!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

                //for bus_no and source entry

                else  if(!s4.isEmpty() && !s2.isEmpty() && s3.isEmpty())
                {
                    db.collection("BusInfo").whereEqualTo("bus_no", s4 + "").whereEqualTo("source",s2+"").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            people.clear();
                            PersonListAdapter   madapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                            mListView.setAdapter(madapter);
                            if (task.isSuccessful()) {
                                StringBuilder sb = new StringBuilder("");
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Person d1=new Person(document.get("bus_no"),document.get("source"),document.get("destination"),document.get("time"));
                                    people.add(d1);
                                }


                            } else {
                                Toast.makeText(getContext(), "error getting doc!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });



                }

                //for source and destination entry
                else if(s4.isEmpty() && !s2.isEmpty() && !s3.isEmpty())
                {
                    db.collection("BusInfo").whereEqualTo("source", s2 + "").whereEqualTo("destination",s3+"").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            people.clear();
                            PersonListAdapter   madapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                            mListView.setAdapter(madapter);
                            if (task.isSuccessful()) {
                                StringBuilder sb = new StringBuilder("");
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Person d1=new Person(document.get("bus_no"),document.get("source"),document.get("destination"),document.get("time"));
                                    people.add(d1);


                                }

                            } else {
                                Toast.makeText(getContext(), "error getting doc!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

                //for bus_no and destination entry
                else if(!s4.isEmpty() && s2.isEmpty() && !s3.isEmpty())
                {
                    db.collection("BusInfo").whereEqualTo("bus_no", s4 + "").whereEqualTo("destination",s3+"").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            people.clear();
                            PersonListAdapter   madapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                            mListView.setAdapter(madapter);
                            if (task.isSuccessful()) {
                                StringBuilder sb = new StringBuilder("");
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Person d1=new Person(document.get("bus_no"),document.get("source"),document.get("destination"),document.get("time"));
                                    people.add(d1);

                                }

                            } else {
                                Toast.makeText(getContext(), "error getting doc!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
                //for all entries
                else if(!s4.isEmpty() && !s2.isEmpty() )
                {

                    db.collection("BusInfo").whereEqualTo( "bus_no", s4 + "").whereEqualTo("source",s2+"").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            people.clear();
                            PersonListAdapter   madapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                            mListView.setAdapter(madapter);
                            if (task.isSuccessful()) {
                                StringBuilder sb = new StringBuilder("");
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Person d1=new Person(document.get("bus_no"),document.get("source"),document.get("destination"),document.get("time"));
                                    people.add(d1);
                                }

                            } else {
                                Toast.makeText(getContext(), "error getting doc!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

                //for all empty
                else if(s4.isEmpty() && s2.isEmpty() && s3.isEmpty())
                {
                    t4.setError("Enter atleast one field.");
                    t4.requestFocus();
                }


                PersonListAdapter  adapter=new PersonListAdapter((Context) getContext(), R.layout.adapter_view_layout,people);
                mListView.setAdapter(adapter);


            }
        });
    }
}
