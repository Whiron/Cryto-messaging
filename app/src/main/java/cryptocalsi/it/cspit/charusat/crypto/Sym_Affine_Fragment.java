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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Sym_Affine_Fragment extends Fragment {

    EditText input_et,input_k1,input_k2;
    TextView output_tv;
    String copy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Affine Cipher");
        return inflater.inflate(R.layout.sym_3,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        input_et=(EditText)view.findViewById(R.id.plaintext_input);
        input_k1=(EditText)view.findViewById(R.id.key_input_1);
        input_k2=(EditText)view.findViewById(R.id.key_input_2);
        output_tv=(TextView)view.findViewById(R.id.output);

        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(input_et.getText().toString().equals("")||input_k1.getText().toString().equals("")||input_k2.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    String inp=input_et.getText().toString();
                    int k1=Integer.valueOf(input_k1.getText().toString());
                    int k2=Integer.valueOf(input_k2.getText().toString());
                    String temp="";

                    for (int i = 0; i < inp.length(); i++)
                    {
                        temp = temp + (char) ((((k1 * inp.charAt(i)) + k2) % 26) + 65);
                    }
                    copy=temp;
                    output_tv.setText("CipherText : "+copy);
                }

            }
        });

        Button decryption=(Button)view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(input_et.getText().toString().equals("")||input_k1.getText().toString().equals("")||input_k2.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    String inp=input_et.getText().toString();
                    int k1=Integer.valueOf(input_k1.getText().toString());
                    int k2=Integer.valueOf(input_k2.getText().toString());
                    String temp="";
                    int k1_inv=0,flag=0;

                    for (int i = 0; i < 26; i++)
                    {
                        flag = (k1 * i) % 26;
                        if (flag == 1)
                        {
                            k1_inv = i;
                        }
                    }
                    for (int i = 0; i < inp.length(); i++)
                    {
                        temp = temp + (char) (((k1_inv * ((inp.charAt(i) - k2)) % 26)) + 65);
                    }
                    copy=temp;
                    output_tv.setText("PlainText : "+copy);
                }

            }
        });

        ImageView swap=(ImageView)view.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_et.setText(copy,TextView.BufferType.EDITABLE);
            }
        });
    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}
