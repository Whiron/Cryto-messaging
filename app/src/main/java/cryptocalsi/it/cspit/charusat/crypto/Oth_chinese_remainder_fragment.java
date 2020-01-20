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

import java.util.Arrays;
import java.util.Objects;

public class Oth_chinese_remainder_fragment extends Fragment {

    EditText pa,pb,pc,pr1,pr2,pr3,pn0,pt;
    Button psolve;
    int x,y,subx,suby,t,n0,x1,y1,y2,x0,y0,ia,ib,ic,ir1,ir2,ir3,i,j;
    String sn=new String();
    String st=new String();

    int[] n = {3, 5, 7};
    int[] a = {2, 3, 2};
    int ans;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Chinese Remainder");
        return inflater.inflate(R.layout.oth_crt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        pa=(EditText)view.findViewById(R.id.a);
        pb=(EditText)view.findViewById(R.id.b);
        pc=(EditText)view.findViewById(R.id.c);
        pr1=(EditText)view.findViewById(R.id.r1);
        pr2=(EditText)view.findViewById(R.id.r2);
        pr3=(EditText)view.findViewById(R.id.r3);
        pn0=(EditText)view.findViewById(R.id.n);
        //pt=(EditText)findViewById(R.id.t);
        psolve=(Button) view.findViewById(R.id.solve);

        try{
            psolve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    n[0]=Integer.parseInt(pa.getText().toString());
                    n[1]=Integer.parseInt(pb.getText().toString());
                    n[2]=Integer.parseInt(pc.getText().toString());
                    a[0]=Integer.parseInt(pr1.getText().toString());
                    a[1]=Integer.parseInt(pr2.getText().toString());
                    a[2]=Integer.parseInt(pr3.getText().toString());

                    ans=chineseRemainder(n, a);
                    pn0.setText(""+ans);


                }
            });


        }
        catch(Exception e)
        {

        }


    }

    public static int chineseRemainder(int[] n, int[] a) {

        int prod = Arrays.stream(n).reduce(1, (i, j) -> i * j);

        int p, sm = 0;
        for (int i = 0; i < n.length; i++) {
            p = prod / n[i];
            sm += a[i] * mulInv(p, n[i]) * p;
        }
        return sm % prod;
    }

    private static int mulInv(int a, int b) {
        int b0 = b;
        int x0 = 0;
        int x1 = 1;

        if (b == 1)
            return 1;

        while (a > 1) {
            int q = a / b;
            int amb = a % b;
            a = b;
            b = amb;
            int xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }

        if (x1 < 0)
            x1 += b0;

        return x1;
    }


    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}
