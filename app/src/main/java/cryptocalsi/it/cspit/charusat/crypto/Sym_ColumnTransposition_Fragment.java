package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Sym_ColumnTransposition_Fragment extends Fragment {

    EditText input_et,key_et;
    TextView output_tv;
    String copy;
    columnarTranspose columnarTranspose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Column Transposition Cipher");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView setKeyTitle=(TextView)view.findViewById(R.id.key_label);
        setKeyTitle.setText("Key Value : ");
        key_et=(EditText)view.findViewById(R.id.key_input);
        key_et.setHint("Key For Permutation");
        key_et.setInputType(InputType.TYPE_CLASS_TEXT);
        input_et=(EditText)view.findViewById(R.id.plaintext_input);
        output_tv=(TextView)view.findViewById(R.id.output);
        columnarTranspose=new columnarTranspose();
        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {

                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();
                    copy=columnarTranspose.encryptCT(key_string,input_string).toUpperCase();
                    output_tv.setText("CipherText : "+copy);
                }
            }
        });
        Button decryption=(Button)view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();
                    copy=columnarTranspose.decryptCT(key_string,input_string);
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

class columnarTranspose{
    public String encryptCT(String key, String text) {
        int[] arrange = arrangeKey(key);

        int lenkey = arrange.length;
        int lentext = text.length();

        int row = (int) Math.ceil((double) lentext / lenkey);

        char[][] grid = new char[row][lenkey];
        int z = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (lentext == z) {
                    // at random alpha for trailing null grid
                    grid[x][y] = RandomAlpha();
                    z--;
                } else {
                    grid[x][y] = text.charAt(z);
                }

                z++;
            }
        }
        String enc = "";
        for (int x = 0; x < lenkey; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (x == arrange[y]) {
                    for (int a = 0; a < row; a++) {
                        enc = enc + grid[a][y];
                    }
                }
            }
        }
        return enc;
    }

    public String decryptCT(String key, String text) {
        int[] arrange = arrangeKey(key);
        int lenkey = arrange.length;
        int lentext = text.length();

        int row = (int) Math.ceil((double) lentext / lenkey);

        String regex = "(?<=\\G.{" + row + "})";
        String[] get = text.split(regex);

        char[][] grid = new char[row][lenkey];

        for (int x = 0; x < lenkey; x++) {
            for (int y = 0; y < lenkey; y++) {
                if (arrange[x] == y) {
                    for (int z = 0; z < row; z++) {
                        grid[z][y] = get[arrange[y]].charAt(z);
                    }
                }
            }
        }

        String dec = "";
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenkey; y++) {
                dec = dec + grid[x][y];
            }
        }

        return dec;
    }

    public char RandomAlpha() {
        //generate random alpha for null space
        Random r = new Random();
        return (char)(r.nextInt(26) + 'a');
    }

    public int[] arrangeKey(String key) {
        //arrange position of grid
        String[] keys = key.split("");
        Arrays.sort(keys);
        int[] num = new int[key.length()];
        for (int x = 0; x < keys.length; x++) {
            for (int y = 0; y < key.length(); y++) {
                if (keys[x].equals(key.charAt(y) + "")) {
                    num[y] = x;
                    break;
                }
            }
        }

        return num;
    }

}
