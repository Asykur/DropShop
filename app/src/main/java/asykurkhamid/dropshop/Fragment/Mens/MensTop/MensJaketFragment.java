package asykurkhamid.dropshop.Fragment.Mens.MensTop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import asykurkhamid.dropshop.Adapter.CatalogAdapter;
import asykurkhamid.dropshop.Pojo.CatalogProduk;
import asykurkhamid.dropshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MensJaketFragment extends Fragment {

    private DatabaseReference reference;
    private DatabaseReference mDatabase;
    private RecyclerView rvJacket;
    private View view;
    private String path1 = "Pria";
    private String path2 = "Atasan Pria";
    private String path3 = "Jaket Pria";

    public MensJaketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mens_jaket, container, false);
        rvJacket = view.findViewById(R.id.rvMensJacket);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showMensJacketCatalog();
    }

    private void showMensJacketCatalog() {
        reference = FirebaseDatabase.getInstance().getReference();
        mDatabase = reference.child("Products").child("Product").child(path1).child(path2).child(path3);
        mDatabase.keepSynced(true);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<CatalogProduk> jaket = new ArrayList<>();
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        Object id = childSnapshot.getKey();
                        String pID = id.toString();

                        Object name = childSnapshot.child("name").getValue();
                        Object price = childSnapshot.child("price").getValue();
                        Object def_image = childSnapshot.child("def_image").getValue();
                        Object desc = childSnapshot.child("description").getValue();
                        Object weight = childSnapshot.child("weight").getValue();

                        String pName = (name.toString());
                        String pPrice = (price.toString());
                        String pDef_Image = (def_image.toString());
                        String descrip = (desc.toString());
                        String weights = (weight.toString());

                        jaket.add(new CatalogProduk(pID, pName, pPrice,pDef_Image,descrip,weights,path1,path2,path3));

                    }

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    int column = 2;
                    rvJacket.setLayoutManager(new GridLayoutManager(getContext(),column));
                    CatalogAdapter adapter = new CatalogAdapter(getContext(),jaket);
                    rvJacket.setHasFixedSize(true);
                    rvJacket.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
