package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mayankgupta.save_data.DataBaseAdapter;
import com.example.mayankgupta.save_data.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Shared extends Fragment {

    EditText name,rollno,email;
    Button save_internal,save_external,load_int,load_ext;
    View rootView;
    com.example.mayankgupta.save_data.DataBaseAdapter DataBaseAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_shared, container, false);
        name=(EditText)rootView.findViewById(R.id.editTextname);
        rollno=(EditText)rootView.findViewById(R.id.editText2);
        email=(EditText)rootView.findViewById(R.id.editText3);
        save_internal=(Button)rootView.findViewById(R.id.Isave);
        save_external=(Button)rootView.findViewById(R.id.Esave);

        final String Name,Rollno,Email;
        Name=name.getText().toString();
        Rollno=rollno.getText().toString();
        Email=email.getText().toString();
        final String filename="SavingData";
        save_internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getActivity().getFilesDir(), filename);
                FileOutputStream outputStream;
                try {
                    outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(Name.getBytes());
                    outputStream.write(Rollno.getBytes());
                    outputStream.write(Email.getBytes());
                    name.setText(" ");
                    rollno.setText(" ");
                    email.setText(" ");
                    outputStream.close();
                    Toast.makeText(getActivity(),"saved in internal storage",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        save_external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file;
                FileOutputStream outputStream;
                try {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        File store = Environment.getExternalStorageDirectory();
                        File directory = new File(store.getAbsolutePath());

                        file = new File(directory, "External_file1.txt");
                        outputStream = new FileOutputStream(file);
String a1=name.getText().toString();
                        a1=a1+"\n"+rollno.getText().toString()+"\n"+email.getText().toString();

                        outputStream.write(a1.getBytes());
                        name.setText(" ");
                        rollno.setText(" ");
                        email.setText(" ");
                        outputStream.close();
                        Toast.makeText(getActivity(),"saved in external storage",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"External storage not mounted",Toast.LENGTH_SHORT).show();
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

}
