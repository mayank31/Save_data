package layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mayankgupta.save_data.MainActivity;
import com.example.mayankgupta.save_data.R;


public class IntExt extends Fragment {

    EditText name,rollno,email;
    Button save;
    View rootView;
    SharedPreferences sharedpreferences;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public IntExt() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_int_ext, container, false);

        name=(EditText)rootView.findViewById(R.id.editTextname);
        rollno=(EditText)rootView.findViewById(R.id.editText2);
        email=(EditText)rootView.findViewById(R.id.editText3);

        save=(Button)rootView.findViewById(R.id.save);
        sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n  = name.getText().toString();
                String ph  = rollno.getText().toString();
                String e  = email.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                 name.setText(" ");
                 rollno.setText(" ");
                email.setText(" ");

                editor.apply();
                Toast.makeText(getActivity(),"Thanks",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;

    }



}
