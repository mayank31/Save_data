package layout;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mayankgupta.save_data.DataBaseAdapter;
import com.example.mayankgupta.save_data.R;


public class WithDB extends Fragment {

    EditText name,rollno,email;
    Button save,retrieve,update,delete;
    View rootView;
    DataBaseAdapter DataBaseAdapter;
    public WithDB() {
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
        rootView=inflater.inflate(R.layout.fragment_with_db, container, false);

        DataBaseAdapter = new DataBaseAdapter(getActivity());
        DataBaseAdapter = DataBaseAdapter.open();
        name=(EditText)rootView.findViewById(R.id.editTextname);
        rollno=(EditText)rootView.findViewById(R.id.editText2);
        email=(EditText)rootView.findViewById(R.id.editText3);
        save=(Button)rootView.findViewById(R.id.save);
        retrieve=(Button)rootView.findViewById(R.id.retreive);
        update=(Button)rootView.findViewById(R.id.Update);
        delete=(Button)rootView.findViewById(R.id.delete);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = name.getText().toString();
                String Rollno = rollno.getText().toString();
                String Email = email.getText().toString();
                DataBaseAdapter.insertEntry(Name, Email,Rollno);
                Toast.makeText(getActivity(),"DataSaved",Toast.LENGTH_SHORT).show();
            }
        });
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Rollno = rollno.getText().toString();
                Cursor cursor=DataBaseAdapter.getData(Rollno);
                if(cursor.getCount()<1)
                {
                    Toast.makeText(getActivity(),"No Data Exist",Toast.LENGTH_SHORT).show();
                }
                else {
                    name.setText(cursor.getString(1));
                    // rollno.setText(cursor.getString(0));
                    email.setText(cursor.getString(2));
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Rollno = rollno.getText().toString();
                DataBaseAdapter.deleteEntry(Rollno);
                Toast.makeText(getActivity(),"Data deleted",Toast.LENGTH_SHORT).show();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Rollno = rollno.getText().toString();
                Cursor cursor=DataBaseAdapter.getData(Rollno);
                if(cursor.getCount()<1)
                {
                    Toast.makeText(getActivity(),"Data does not exist",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String Name=name.getText().toString();
                    String Email=email.getText().toString();
                    DataBaseAdapter.updateEntry(Name,Email,Rollno);
                    Toast.makeText(getActivity(),"Data updated",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return rootView;
    }


}
