package asykurkhamid.dropshop.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class UpdateActivity extends ParentClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setDefaultToolbar(true);
    }
}
