package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Asym_Diffie_Hellman_Fragment extends Fragment {

    EditText prime_number,primitive_root,val_x,val_y;
    TextView print;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Diffie Hellman Cipher");
        return inflater.inflate(R.layout.asym_diffie_hellman,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        prime_number=(EditText)view.findViewById(R.id.primenumber_input);
        primitive_root=(EditText)view.findViewById(R.id.primitive_root_input);
        val_x=(EditText)view.findViewById(R.id.value_x_input);
        val_y=(EditText)view.findViewById(R.id.value_y_input);
        print=(TextView)view.findViewById(R.id.output);

        Button key=(Button)view.findViewById(R.id.button_key);
        key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(prime_number.getText().toString().equals("")||primitive_root.getText().toString().equals("")||val_x.getText().toString().equals("")
                        ||val_y.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();

                }

                else {
                    int prime=Integer.valueOf(prime_number.getText().toString());
                    double primitive=Double.valueOf(primitive_root.getText().toString());
                    double x=Double.valueOf(val_x.getText().toString());
                    double y=Double.valueOf(val_y.getText().toString());

                    int Ya=(int)((Math.pow(primitive,x))%prime);
                    int Yb=(int)((Math.pow(primitive,y))%prime);

                    int Ka=(int)((Math.pow(Yb,x))%prime);
                    int Kb=(int)((Math.pow(Ya,x))%prime);

                    if (Ka==Kb){
                        print.setText("Transmission Successful");
                    }

                    else
                        print.setText("Transmission Failed");
                }

            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}
