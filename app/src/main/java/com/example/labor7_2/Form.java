package com.example.labor7_2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labor7_2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;
import java.text.Normalizer;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Form.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Form#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Form extends Fragment {


    Spinner spinner,spinner2;
    Calendar c;
    DatePickerDialog dpd;
    Button nBtn,gallery,camera,savedata,openList;
    TextView nTv,datet;
    RadioGroup radioGroup, radioGroup2;
    RadioButton radioButton, radioButton2;
    ImageView img1;
    Uri imageUri;
    DatabaseReference dbform;
    EditText ename;
    CheckBox cb;
    //private static final  int RESULT_OK = 100;

    static final int REQUEST_IMAGE_CAPTURE = 100;
    static final int PICK_IMAGE = 50;

    private void dispatchTakePictureIntent() {
        //PackageManager pm = getActivity().getPackageManager();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        img1 = (ImageView)getView().findViewById(R.id.imageView);
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img1.setImageBitmap(imageBitmap);
        }
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK){
            imageUri = data.getData();
            img1.setImageURI(imageUri);
        }
    }
    private void opGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    private void addForm ()
    {

        String id=dbform.push().getKey();

        ename=(EditText)getView().findViewById(R.id.editText);
        String name=ename.getText().toString();

        spinner = (Spinner)getView().findViewById(R.id.spinner);
        String location = spinner.getSelectedItem().toString();

        String profile_picture ="nemtudom";

        datet=(TextView)getView().findViewById(R.id.textView3);
        String date = (String) datet.getText();

        radioGroup = (RadioGroup)getView().findViewById(R.id.rdg);
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = getView().findViewById(radioID);
        String gender = (String) radioButton.getText();

        String hobbies;
        cb=(CheckBox)getView().findViewById(R.id.checkBox);
        if(cb.isChecked()==true){
            hobbies="yes";
        }
        else
        {
            hobbies="false";
        }

        spinner2 = (Spinner)getView().findViewById(R.id.spinner2);
        String department = spinner2.getSelectedItem().toString();

        radioGroup2 = (RadioGroup)getView().findViewById(R.id.rdg2);
        int radioID2 = radioGroup2.getCheckedRadioButtonId();
        radioButton2 = getView().findViewById(radioID2);
        String yos = (String) radioButton.getText();

        Fire_Form form = new Fire_Form(id,name,location,profile_picture,date,gender,hobbies,department,yos);
        dbform.child(id).setValue(form);

        Toast.makeText(getActivity(),"Saved", Toast.LENGTH_SHORT).show();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_form, container, false);
        dbform = FirebaseDatabase.getInstance().getReference("forms");
        //View view1 = inflater.inflate(R.layout.fragment_form, container, false);
        nTv =(TextView)v.findViewById(R.id.textView3);
        nBtn =(Button)v.findViewById(R.id.button3);
        camera = (Button)v.findViewById(R.id.button2);
        gallery=(Button)v.findViewById(R.id.button);
        savedata=(Button)v.findViewById(R.id.button4);
        openList=(Button)v.findViewById(R.id.button5);
        nBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int nYear, int nMonth, int nDay) {
                        nTv.setText(nDay + "/" + (nMonth+1)+"/"+ nYear);
                    }
                },day,month,year);
                dpd.show();

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opGallery();

            }
        });
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addForm();

            }
        });
        openList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentTransaction frag_trans = getActivity().getSupportFragmentManager().beginTransaction();
                frag_trans.add(R.id.fragment_container,new List());
                frag_trans.commit();*/
                List listfrag= new List();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, listfrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });
        /*RADIOGROUP*/
        radioGroup = (RadioGroup)v.findViewById(R.id.rdg);
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = v.findViewById(radioID);
        /*String gender = (String) radioButton.getText();*/

        /*SPINNER 1*/
        spinner = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /*SPINNER 2*/
        Spinner spinner2 = (Spinner)v.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.departments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        /*RADIOGROUP 2*/
        radioGroup2 = (RadioGroup)v.findViewById(R.id.rdg2);
        int radioID2 = radioGroup2.getCheckedRadioButtonId();
        radioButton2 = v.findViewById(radioID2);
        /*String gender = (String) radioButton2.getText();*/

        return v;

    }




}
