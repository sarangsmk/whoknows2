package gq.smktech.whoknows;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import gq.smktech.whoknows.Ask_Activity.*;

public class activity_dialog extends AppCompatDialogFragment {
    private EditText txtMobile;
    private DialogListner listner;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view)
                .setTitle("Offline Notification")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mobileNumber = "0";
                        listner.applyTexts(mobileNumber);
                    }
                })
                .setPositiveButton("Notify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mobileNumber = txtMobile.getText().toString();
                        listner.applyTexts(mobileNumber);

                    }
                });
        txtMobile = view.findViewById(R.id.mobile);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listner = (DialogListner) context;
        }catch(ClassCastException e)
        {
            throw new ClassCastException(context.toString()+"must implement DialogListner");
        }
    }

    public interface DialogListner{
        void applyTexts(String mob);
    }
}
