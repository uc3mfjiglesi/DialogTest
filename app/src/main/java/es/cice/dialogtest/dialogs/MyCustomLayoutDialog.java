package es.cice.dialogtest.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import es.cice.dialogtest.R;

/**
 * Created by cice on 25/1/17.
 */

public class MyCustomLayoutDialog extends DialogFragment {

    private CustomDialogInterface mDialogInterface;
    public static final String EMAIL_KEY="email";
    public static final String NAME_KEY="name";
    private EditText nameET,emailET;

    public interface CustomDialogInterface{
        public void setData(Map<String,String> data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mDialogInterface = (CustomDialogInterface) context;
        }catch(ClassCastException e){
            throw new ClassCastException(" el context debe implementar el interfaz CustomDialogInterface");
        }
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View layout=getActivity().getLayoutInflater().inflate(R.layout.custom_dialog_layout,null);
        nameET= (EditText) layout.findViewById(R.id.nombreET);
        emailET= (EditText) layout.findViewById(R.id.emailET);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setMessage("Custom Layout Dialog")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Map<String,String> data=new HashMap<>();
                        data.put(NAME_KEY,nameET.getText().toString());
                        data.put(EMAIL_KEY,emailET.getText().toString());
                        mDialogInterface.setData(data);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setView(layout);

        return builder.create();
    }
}
