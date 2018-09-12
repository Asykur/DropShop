package asykurkhamid.dropshop.Fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import asykurkhamid.dropshop.Adapter.PaymentConfirmationAdapter;
import asykurkhamid.dropshop.Pojo.DataPaymentConfirmation;
import asykurkhamid.dropshop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentConfFragment extends Fragment {

    private RecyclerView rvPayment;
    private DatabaseReference DbPayRef;
    private ArrayList<DataPaymentConfirmation> data = new ArrayList<>();
    private DataPaymentConfirmation conf;
    private String UID;

    public PaymentConfFragment() {
        // Required empty public constructor
    }

    DataPaymentConfirmation getKey = new DataPaymentConfirmation();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_conf, container, false);

        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        rvPayment = view.findViewById(R.id.rvConfPayment);
        DbPayRef = FirebaseDatabase.getInstance().getReference().child("Payment_Confirmation").child(UID);
        DbPayRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    data.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        conf = snapshot.getValue(DataPaymentConfirmation.class);
                        getKey.setID(snapshot.getKey());
                        data.add(conf);
                    }
                    initRecycler();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPayment.setLayoutManager(layoutManager);
        rvPayment.setHasFixedSize(true);
        PaymentConfirmationAdapter adapter = new PaymentConfirmationAdapter(data);
        rvPayment.setAdapter(adapter);
    }

}
